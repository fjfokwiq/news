package com.example.fjfokwiq.news.ui.activity;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fjfokwiq.news.ui.adapter.GuidePagerAdapter;
import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.ui.base.BaseActivity;
import com.example.fjfokwiq.news.utlis.CommonUtil;
import com.example.fjfokwiq.news.utlis.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity  extends BaseActivity implements ViewPager.OnPageChangeListener {
    private ViewPager pager;
    private LinearLayout layout;
    private int[] img = {R.drawable.bd, R.drawable.small, R.drawable.wy, R.drawable.welcome};
    private GuidePagerAdapter adapter;
    private ImageView[] point;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new StatusBarUtil().setTransparentStatusBar(this,0);
        super.onCreate(savedInstanceState);
        boolean first= CommonUtil.getPreferences().getBoolean("first", true);
        if (!first) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

        initView();
        initData();
        initPoint();
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);

    }

    @Override
    protected int setUiLayout() {
        return R.layout.guide_layout;
    }


    private void initPoint() {
        point = new ImageView[img.length];
        resetPoint();
        int currentItem = 0;
        point[currentItem].setImageResource(R.drawable.point1);
    }


    private void initData() {
        List<View> views = new ArrayList<>();
        for (int anImg : img) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(anImg);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            image.setLayoutParams(params);
            views.add(image);
        }
        adapter = new GuidePagerAdapter(views);
    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.vp_pager);
        layout = (LinearLayout) findViewById(R.id.ll_lin);
    }


    private void resetPoint() {
        for (int i = 0; i < layout.getChildCount(); i++) {
            point[i] = (ImageView) layout.getChildAt(i);
            point[i].setImageResource(R.drawable.point2);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        new ArgbEvaluator().evaluate(positionOffset, R.color.colorAccent, R.color.colorPrimary);
    }

    @Override
    public void onPageSelected(int position) {
        resetPoint();
        point[position].setImageResource(R.drawable.point1);
        if (position >= 3) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
            overridePendingTransition(android.R.anim.slide_in_left,
                    android.R.anim.slide_in_left);
            CommonUtil.getEditor().putBoolean("first", false);
            CommonUtil.getEditor().apply();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}


