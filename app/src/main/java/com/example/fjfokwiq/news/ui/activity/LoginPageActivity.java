package com.example.fjfokwiq.news.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.ui.base.BaseActivity;


public class LoginPageActivity extends BaseActivity {


    private TextView reg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        reg = (TextView) findViewById(R.id.tv_reg);
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
                startActivity(RegisterPageActivity.newIntent(LoginPageActivity.this));
            }
        }, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        reg.setText(spannableString);
        reg.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public static Intent openLogin(Context context) {
        return new Intent(context, LoginPageActivity.class);
    }

    @Override
    protected int setUiLayout() {
        return R.layout.user_layout;
    }
}
