package com.example.fjfokwiq.news.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.ui.base.BaseActivity;
import com.example.fjfokwiq.news.utlis.StatusBarUtil;

public class RegisterPageActivity extends BaseActivity {


    @Override
    protected int setUiLayout() {
        return R.layout.activity_register_page;
    }

    public static Intent newIntent(Context context){
        return new Intent(context, RegisterPageActivity.class);
    }

}
