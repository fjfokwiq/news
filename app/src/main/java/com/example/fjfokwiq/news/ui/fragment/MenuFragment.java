package com.example.fjfokwiq.news.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.menuItemData;
import com.example.fjfokwiq.news.ui.activity.ImageSelectActivity;
import com.example.fjfokwiq.news.ui.activity.LoginPageActivity;
import com.example.fjfokwiq.news.ui.adapter.RecyclerMenuAdapter;
import com.example.fjfokwiq.news.utlis.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MenuFragment extends Fragment {

    private ImageView login;
    private RecyclerView menuList;

    private RecyclerMenuAdapter adapter;
    private TextView username;
    private int[] menuIcon = {R.drawable.biz_navigation_tab_news,
            R.drawable.biz_navigation_tab_local_news,
            R.drawable.biz_navigation_tab_pics,
            R.drawable.biz_navigation_tab_read,
            R.drawable.biz_navigation_tab_ties};
    private String[] menuName = {"新闻", "地址", "图片", "收藏", "信息"};

    private CardView card;

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
        username = (TextView) view.findViewById(R.id.tv_username);
        card = (CardView) view.findViewById(R.id.cv_reset_login);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMenuItem();
        initLogin();
        logOut();
        String path = CommonUtil.getPreferences().getString("imagePath", "");
        if (!TextUtils.isEmpty(path)) {
            Glide.with(getActivity()).load(new File(path)).into(login);

        }
        if (!TextUtils.isEmpty(CommonUtil.getPreferences().getString("text", ""))) {
            username.setText(CommonUtil.getPreferences().getString("text", ""));
        }
    }

    /*
    * 注销
    * */
    private void logOut() {
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.getEditor().putBoolean("isLogin", false).apply();
                CommonUtil.getEditor().putString("text", "").apply();
                username.setText("");
                Glide.with(getActivity())
                        .load(R.drawable.biz_pc_main_info_profile_avatar_bg_dark).into(login);
            }
        });
    }

    private void initLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogin()) {
                    startActivityForResult(LoginPageActivity.openLogin(getActivity()), CommonUtil.REQUEST_CODE_LOGIN);
                } else {
                    startActivityForResult(new Intent(getActivity(), ImageSelectActivity.class), CommonUtil.REQUEST_CODE_SELECT);
                }


            }
        });
    }

    public boolean isLogin() {
        return CommonUtil.getPreferences().getBoolean("isLogin", false);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imagePath = null;
        String text = null;
        if (resultCode == RESULT_OK) {
            if (requestCode == CommonUtil.REQUEST_CODE_LOGIN) {
                imagePath = data.getStringExtra("avatarPath");
                text = data.getStringExtra("username");
                CommonUtil.getEditor().putString("text", text).apply();
                username.setText(text);
            } else if (requestCode == CommonUtil.REQUEST_CODE_SELECT) {
                imagePath = data.getStringExtra("ok");
            }
        }
        CommonUtil.getEditor().putString("imagePath", imagePath).apply();
        if (!TextUtils.isEmpty(imagePath)) {
            Glide.with(getActivity()).load(new File(imagePath)).into(login);

        }


    }

    /*初始化菜单项点击*/
    private void initMenuItem() {
        menuList.setLayoutManager(new LinearLayoutManager(getContext()));
        menuList.setAdapter(adapter);
        adapter.setOnMenuItemListener(new RecyclerMenuAdapter.onMenuItemListener() {
            @Override
            public void onMenuItem(int position) {
                SlidingPaneLayout layout = (SlidingPaneLayout) getActivity().findViewById(R.id.layout_draw);
                layout.closePane();
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

    }

    private void initData() {
        List<menuItemData> mList = new ArrayList<>();
        for (int i = 0; i < menuIcon.length; i++) {
            menuItemData modle = new menuItemData();
            modle.menuIcon = menuIcon[i];
            modle.menuName = menuName[i];
            mList.add(modle);
        }
        adapter = new RecyclerMenuAdapter(getContext());
        adapter.addData(mList);
        adapter.notifyDataSetChanged();
    }
}
