package com.example.fjfokwiq.news.utlis;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.fjfokwiq.news.R;

/**
 * Created by fjfokwiq on 2017/4/20.
 */

public class StatusBarUtli {

    private static final int TRANSLUCENT_VIEW_ID = R.id.translucent_view;
    private static final int  IS_PADDING_KEY= 0X45454;

    public void setTransparentStatusBar(Activity activity, int alpha){
        setTransparentStatusBar(activity,alpha,null);
    }

    public void setTransparentStatusBar(Activity activity, int alpha,View topView) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.argb(alpha, 0, 0, 0));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            View statusBarView = contentView.findViewById(TRANSLUCENT_VIEW_ID);
            if (statusBarView != null) {
                if (statusBarView.getVisibility() == View.GONE) {
                    statusBarView.setVisibility(View.VISIBLE);
                }
                statusBarView.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
            } else {
                contentView.addView(createStatusBarView(activity, alpha));
            }

        } else {
            return;
        }
        if (topView != null) {
            Boolean isSetPadding = topView.getTag(IS_PADDING_KEY) != null;
           if (!isSetPadding) {
                topView.setPadding(topView.getPaddingLeft(),
                        topView.getPaddingTop() + getStatusBarHeight(activity),
                        topView.getPaddingRight(),
                        topView.getPaddingBottom());
                topView.setTag(IS_PADDING_KEY,true);
           }

        }

    }

    private View createStatusBarView(Activity activity, int alpha) {
        View statusBarView = new View(activity);
        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(Color.argb(alpha,0,0,0));
        statusBarView.setId(TRANSLUCENT_VIEW_ID);
        return statusBarView;
    }

    private int getStatusBarHeight(Activity activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return activity.getResources().getDimensionPixelSize(resourceId);
    }
}
