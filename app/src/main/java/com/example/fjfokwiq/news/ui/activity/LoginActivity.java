package com.example.fjfokwiq.news.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.ui.base.BaseActivity;

/**
 * Created by fjfokwiq on 2017/5/31.
 */

public class LoginActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    public static Intent openLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected int setUiLayout() {
        return R.layout.login_layout;
    }
}
