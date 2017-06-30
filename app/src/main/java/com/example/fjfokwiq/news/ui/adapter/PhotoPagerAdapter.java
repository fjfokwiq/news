package com.example.fjfokwiq.news.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class PhotoPagerAdapter extends PagerAdapter {
    private List<View> photoList;

    public PhotoPagerAdapter(List<View> photoList) {
        this.photoList = photoList;

    }

    @Override
    public int getCount() {
        return photoList != null ? photoList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(photoList.get(position));
        return photoList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(photoList.get(position));
    }
}
