package com.example.fjfokwiq.news.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.ui.adapter.ContentPagerAdapter;
import com.example.fjfokwiq.news.utlis.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;


public class ContentFragment extends Fragment {
    private TabLayout layout;
    private ViewPager pager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fg_layout, container, false);
        initView(view);
        initContent();
        new StatusBarUtil().setTransparentStatusBar(getActivity(), 0);
        return view;
    }

    private void initView(View view) {
        layout = (TabLayout) view.findViewById(R.id.tl_tab);
        pager = (ViewPager) view.findViewById(R.id.vp_news);
    }


    private void initContent() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new NewsHomeFragment());
        fragmentList.add(new NewsWarFragment());
        fragmentList.add(new NewsSocietyFragment());
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(new ContentPagerAdapter(getChildFragmentManager(), fragmentList));
        layout.setupWithViewPager(pager);



    }
}
