package com.example.fjfokwiq.news.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GuidePagerAdapter extends PagerAdapter {
    private List<View>views;

    public GuidePagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views!=null?views.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
