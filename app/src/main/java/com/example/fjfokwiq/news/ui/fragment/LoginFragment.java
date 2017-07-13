package com.example.fjfokwiq.news.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.api.ApiFactory;
import com.example.fjfokwiq.news.bean.LoginMessage;
import com.example.fjfokwiq.news.ui.activity.ImageSelectActivity;
import com.example.fjfokwiq.news.ui.activity.RegisterPageActivity;
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
    private Button loginPage;
    private String imagePath;
    private TextView reg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_layout, container, false);
        circleImageView = (CircleImageView) view.findViewById(R.id.iv_login_page);
        user = (TextInputLayout) view.findViewById(R.id.et_layout_user);
        pass = (TextInputLayout) view.findViewById(R.id.et_layout_pass);
        loginPage = (Button) view.findViewById(R.id.bt_page_login);
        reg = (TextView) view.findViewById(R.id.tv_reg);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestLogin();
        changeAvatar();
        registerUser();
    }
    /*
    * 跳转注册页面
    * */
    private void registerUser() {
        String text = "没有账号";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Context context=getActivity();
                context.startActivity(RegisterPageActivity.newIntent(context));
            }
        }, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        reg.setText(spannableString);
        reg.setMovementMethod(LinkMovementMethod.getInstance());
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
            if (requestCode==CommonUtil.REQUEST_CODE_SELECT) {
                imagePath = data.getStringExtra("ok");
            }

        }
        if (!imagePath.isEmpty()) {
            Glide.with(getActivity()).load(new File(imagePath)).into(circleImageView);

        }
    }


    private void requestLogin() {
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = user.getEditText().getText().toString();
                String password = pass.getEditText().getText().toString();
                String json = String.valueOf(buildJson(userName, password));
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                    login(json, v);

                } else {
                    CommonUtil.showSnackBar(v, "请输入账号或密码");
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
                            intent.putExtra("username", loginMessage.getUser());
                            getActivity().setResult(RESULT_OK, intent);
                            CommonUtil.getEditor().putBoolean("isLogin", true);
                            CommonUtil.getEditor().apply();
                            getActivity().finish();
                        } else {
                            CommonUtil.showSnackBar(view, "账号或密码错误");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        CommonUtil.showSnackBar(view, "连接服务器失败");
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
