package com.example.fjfokwiq.news.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fjfokwiq.news.BuildConfig;
import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.RecyclerDataModle;
import com.example.fjfokwiq.news.ui.activity.LoginActivity;
import com.example.fjfokwiq.news.ui.activity.MainActivity;
import com.example.fjfokwiq.news.ui.adapter.RecyclerMenuAdapter;
import com.example.fjfokwiq.news.widget.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjfokwiq on 2017/5/20.
 */

public class MenuFragment extends Fragment {

    private ImageView login;
    private RecyclerView menuList;

    private List<RecyclerDataModle> mList;

    private RecyclerMenuAdapter adapter;

    private int[] menuIcon = {R.drawable.biz_navigation_tab_news,
            R.drawable.biz_navigation_tab_local_news,
            R.drawable.biz_navigation_tab_pics,
            R.drawable.biz_navigation_tab_read,
            R.drawable.biz_navigation_tab_ties};
    private String[] menuName = {"新闻", "地址", "图片", "收藏", "信息"};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fg_layout, container, false);
        menuList = (RecyclerView) view.findViewById(R.id.rv_menu);
        login = (ImageView) view.findViewById(R.id.iv_login);
        initMenuItem();
        initLogin();
        return view;
    }

    private void initLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout layout = (DrawerLayout) getActivity().findViewById(R.id.layout_draw);
                layout.closeDrawers();
                startActivity(LoginActivity.openLogin(getActivity()));
            }
        });
    }

    private void initMenuItem() {
        menuList.setLayoutManager(new LinearLayoutManager(getContext()));
        menuList.setAdapter(adapter);
        adapter.setOnMenuItemListener(new RecyclerMenuAdapter.onMenuItemListener() {
            @Override
            public void onMenuItem(int position) {
               DrawerLayout layout = (DrawerLayout) getActivity().findViewById(R.id.layout_draw);
                layout.closeDrawers();
                switch (position) {
                    case 0:

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < menuIcon.length; i++) {
            RecyclerDataModle modle = new RecyclerDataModle();
            modle.menuIcon = menuIcon[i];
            modle.menuName = menuName[i];
            mList.add(modle);
        }
        adapter = new RecyclerMenuAdapter(getContext());
        adapter.addData(mList);
        adapter.notifyDataSetChanged();
    }
}
