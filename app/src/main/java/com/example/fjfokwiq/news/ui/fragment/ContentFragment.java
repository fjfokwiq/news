package com.example.fjfokwiq.news.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.ui.adapter.ContentPagerAdapter;
import com.example.fjfokwiq.news.utlis.StatusBarUtli;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjfokwiq on 2017/5/20.
 */

public class ContentFragment extends Fragment {
    private Toolbar bar;
    private TabLayout layout;
    private ViewPager pager;
    private List<Fragment> fragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fg_layout, container, false);
        initView(view);
        initContent();
        new StatusBarUtli().setTransparentStatusBar(getActivity(),0,bar);
        return view;
    }

    private void initView(View view) {
        bar = (Toolbar) view.findViewById(R.id.tb_bar);
        layout = (TabLayout) view.findViewById(R.id.tl_tab);
        pager = (ViewPager) view.findViewById(R.id.vp_news);
    }


    private void initContent() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsHomeFragment());
        fragmentList.add(new NewsWarFragment());
        fragmentList.add(new NewsSocietyFragement());
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(new ContentPagerAdapter(getChildFragmentManager(), fragmentList));
        layout.setupWithViewPager(pager);


    }
}
