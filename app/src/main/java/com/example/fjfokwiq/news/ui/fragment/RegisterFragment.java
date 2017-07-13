package com.example.fjfokwiq.news.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.api.ApiFactory;
import com.example.fjfokwiq.news.bean.LoginMessage;
import com.example.fjfokwiq.news.ui.activity.LoginPageActivity;
import com.example.fjfokwiq.news.utlis.CommonUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RegisterFragment extends Fragment {
    private EditText reg_user;
    private EditText reg_pass;
    private Button bt_reg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_layout, container, false);
        reg_pass = (EditText) view.findViewById(R.id.et_reg_password);
        reg_user = (EditText) view.findViewById(R.id.et_reg_user);
        bt_reg = (Button) view.findViewById(R.id.bt_reg);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = reg_user.getText().toString();
                String pass = reg_pass.getText().toString();
                String regInfo = "{" + "\"username\":" + user + "," + "\"password\":" + pass + "}";
                if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(regInfo)) {
                    register(regInfo,v);
                }else {
                    CommonUtil.showSnackBar(v,"请输入注册的账号或密码");
                }
            }
        });

    }

    /*
    * 发送注册请求
    * */
    private void register(String regInfo, final View v) {
        ApiFactory.getLoginService()
                .userReg(regInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginMessage>() {
                    @Override
                    public void accept(@NonNull LoginMessage loginMessage) throws Exception {
                        if (loginMessage.getResult().equals("ok")) {
                            CommonUtil.showToast(getActivity(), "注册成功");
                            getActivity().finish();
                        } else {
                            CommonUtil.showSnackBar(v,"注册失败");
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        CommonUtil.showSnackBar(v,"服务端错误");

                    }
                });


    }


}
