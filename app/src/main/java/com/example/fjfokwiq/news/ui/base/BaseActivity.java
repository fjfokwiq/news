package com.example.fjfokwiq.news.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by fjfokwiq on 2017/5/1.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        getSupportActionBar().hide();

        if (setUiLayout()!=0) {
            setContentView(setUiLayout());
        }
    }



    protected abstract int setUiLayout();

}
