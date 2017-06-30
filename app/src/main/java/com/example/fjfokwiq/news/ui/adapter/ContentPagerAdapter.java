package com.example.fjfokwiq.news.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class ContentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mData;

    public ContentPagerAdapter(FragmentManager fm, List<Fragment> mData) {
        super(fm);
        this.mData = mData;

    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData!=null?mData.size():0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "主页";
            case 1:
                return "社会";
            case 2:
                return "军事";

        }
        return null;
    }
}
