package com.example.fjfokwiq.news.ui.activity;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.ui.base.BaseActivity;

import com.example.fjfokwiq.news.utlis.CommonUtil;

import java.lang.reflect.Field;


public class MainActivity extends BaseActivity {
    private ImageView home;
    private ImageView share;
    private SlidingPaneLayout layout;
    private long currentTime;
    private PopupMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initMenu();
        initShare();


    }

    private void initShare() {
        share.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                menu = new PopupMenu(MainActivity.this, v);
                menu.getMenuInflater().inflate(R.menu.popup_menu, menu.getMenu());

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.pop_qq:
                                break;
                            case R.id.pop_friends:
                                break;
                            case R.id.pop_weibo:
                                break;
                            case R.id.pop_weixin:
                                break;

                        }
                        return true;
                    }
                });
                try {
                    Field field = menu.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    MenuPopupHelper helper = (MenuPopupHelper) field.get(menu);
                    helper.setForceShowIcon(true);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                menu.show();


            }
        });
    }

    /*
    * 双击返回退出
    * */
    @Override
    public void onBackPressed() {
        long time = System.currentTimeMillis();
        if (time - currentTime > 2000) {
            if (layout.isOpen()) {
                layout.closePane();
            } else {
                CommonUtil.showToast(MainActivity.this, "再按一次退出");
                currentTime = time;
            }
        } else {
            super.onBackPressed();
        }

    }

    private void initMenu() {
        layout.setSliderFadeColor(0);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout.isOpen()) {
                    layout.closePane();
                } else {
                    layout.openPane();
                }

            }
        });

    }


    @Override
    protected int setUiLayout() {
        return R.layout.activity_main;
    }


    private void initView() {
        home = (ImageView) findViewById(R.id.iv_home);
        share = (ImageView) findViewById(R.id.iv_share);
        layout = (SlidingPaneLayout) findViewById(R.id.layout_draw);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
