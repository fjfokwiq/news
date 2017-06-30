package com.example.fjfokwiq.news.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.fjfokwiq.news.utlis.StatusBarUtil;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        setContentView(setUiLayout());
        StatusBarUtil util = new StatusBarUtil();
        util.setTransparentStatusBar(this, 0);

    }


    protected abstract int setUiLayout();

}
