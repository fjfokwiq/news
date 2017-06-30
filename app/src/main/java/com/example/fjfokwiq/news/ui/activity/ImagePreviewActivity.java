package com.example.fjfokwiq.news.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.ui.adapter.PhotoPagerAdapter;
import com.example.fjfokwiq.news.ui.base.BaseActivity;
import com.example.fjfokwiq.news.widget.PhotoViewPager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

public class ImagePreviewActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private PhotoViewPager pager;
    private static List<String> pathList;
    private static int currentPosition;
    private List<View> views;
    private TextView count;

    @Override
    protected int setUiLayout() {
        return R.layout.activtiy_preview_layout;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initPager();

    }


    /*初始化viewpager数据*/
    private void initData() {
        views = new ArrayList<>();
        for (int i = 0; i < pathList.size(); i++) {
            PhotoView photo = new PhotoView(ImagePreviewActivity.this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            photo.setLayoutParams(params);
            Glide.with(ImagePreviewActivity.this)
                    .load(new File(pathList.get(i)))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(photo);
            views.add(photo);

        }
    }


    private void initPager() {
        count.setText(currentPosition + "/" + pathList.size());
        PhotoPagerAdapter adapter = new PhotoPagerAdapter(views);
        pager.setAdapter(adapter);
        pager.setCurrentItem(currentPosition);
        pager.addOnPageChangeListener(this);
    }


    private void initView() {
        count = (TextView) findViewById(R.id.tv_count);
        pager = (PhotoViewPager) findViewById(R.id.vp_photo);
    }

    /*跳转当前activity*/
    public static Intent newsIntent(Activity context, int position, List<String> imagePath) {
        pathList=imagePath;
        currentPosition=position;
        return new Intent(context, ImagePreviewActivity.class);
    }


    /*viewpager滑动监听*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        count.setText((position+1) + "/" + pathList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
