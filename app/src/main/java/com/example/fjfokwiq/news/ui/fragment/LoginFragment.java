package com.example.fjfokwiq.news.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.api.ApiFactory;
import com.example.fjfokwiq.news.bean.LoginMessage;
import com.example.fjfokwiq.news.ui.activity.ImageSelectActivity;
import com.example.fjfokwiq.news.utlis.CommonUtil;
import com.example.fjfokwiq.news.widget.CircleImageView;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;


public class LoginFragment extends Fragment {
    private CircleImageView circleImageView;
    private TextInputLayout user, pass;
    private Button login;
    private String imagePath;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_layout, container, false);
        circleImageView = (CircleImageView) view.findViewById(R.id.iv_login_page);
        user = (TextInputLayout) view.findViewById(R.id.et_layout_user);
        pass = (TextInputLayout) view.findViewById(R.id.et_layout_pass);
        login = (Button) view.findViewById(R.id.bt_login);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestLogin();
        changeAvatar();

    }

    private void changeAvatar() {
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), ImageSelectActivity.class), CommonUtil.REQUEST_CODE_SELECT);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK) {
                   imagePath = data.getStringExtra("ok");
                if (!imagePath.isEmpty()) {
                    Glide.with(getActivity()).load(new File(imagePath)).into(circleImageView);
                }


            }
        }



    private void requestLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = user.getEditText().getText().toString();
                String password = pass.getEditText().getText().toString();
                String json = String.valueOf(buildJson(userName, password));
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                    login(json,v);

                }

            }
        });
    }
    /*
    * 发起登录请求
    * */
    public void login(String json, final View view) {
        ApiFactory.getLoginService()
                .getToken(json)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginMessage>() {
                    @Override
                    public void accept(@NonNull LoginMessage loginMessage) throws Exception {
                        if (loginMessage.getResult().equals("ok")) {
                            Intent intent = new Intent();
                            intent.putExtra("avatarPath", imagePath);
                            intent.putExtra("username",loginMessage.getUser());
                            getActivity().setResult(RESULT_OK,intent);
                            CommonUtil.getEditor().putBoolean("isLogin", true);
                            CommonUtil.getEditor().apply();
                            getActivity().finish();
                        }else {
                            CommonUtil.showSnackBar(view,"账号或密码错误");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        CommonUtil.showSnackBar(view,"连接服务器失败");
                    }
                });


    }
    /*
    * 构建向服务器发送的json字符串
    * */

    public static StringBuilder buildJson(String userName, String password) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\"username\":\"");
        builder.append(userName);
        builder.append("\",");
        builder.append("\"password\":\"");
        builder.append(password);
        builder.append("\"");
        builder.append("}");
        return builder;
    }
}
