package com.example.fjfokwiq.news.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.fjfokwiq.news.R;

public class SlidingMenu extends HorizontalScrollView {
    private LinearLayout mWarp;
    private ViewGroup mMenu;
    private ViewGroup mContent;

    private int screenHeight;
    private int screenWidth;
    private int mMenuWidth;
    private int mMenuRightPadding = (int) dpChange(50);


    private MenuStatus isOpen = MenuStatus.CLOSE;

    private MenuPosition position = MenuPosition.LEFT;
    private static final int MENU_POS_LEFT = 0;
    private static final int MENU_POS_RIGHT = 1;
    private static final int MENU_POS_BOTH = 2;


    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics outMeterics = obtainScreenPixel(context);
        screenWidth = outMeterics.widthPixels;
        screenHeight = outMeterics.heightPixels;
        obtainAttrs(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWarp = (LinearLayout) getChildAt(0);
        mMenu = (ViewGroup) mWarp.getChildAt(0);
        mContent = (ViewGroup) mWarp.getChildAt(1);

        if (position == MenuPosition.LEFT) {
            mMenuWidth = mMenu.getLayoutParams().width = screenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = screenWidth;
            mMenu.setFitsSystemWindows(true);
            mMenu.setClipToPadding(true);

        } else {
            mMenuWidth = mMenu.getLayoutParams().width = screenWidth;
            mContent.getLayoutParams().width = screenWidth - mMenuRightPadding;
            mContent.setFitsSystemWindows(true);
            mContent.setClipToPadding(false);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            if (position == MenuPosition.LEFT) {
                mMenu.layout(0, 0, mMenuWidth, screenHeight);
                mContent.layout(mMenuWidth, 0, mMenuWidth + screenWidth, screenHeight);
                this.scrollTo(mMenuWidth, 0);
            } else if (position == MenuPosition.RIGHT) {
                mMenu.layout(mMenuWidth, 0, mMenuWidth + screenWidth, screenHeight);
                mContent.layout(0, 0, screenWidth, screenHeight);

            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                float scrollX = getScrollX();
                if (scrollX >= mMenuWidth / 2) {
                    this.scrollTo(mMenuWidth, 0);
                    isOpen = MenuStatus.CLOSE;
                } else {
                    this.smoothScrollTo(0, 0);
                    isOpen = MenuStatus.OPEN;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }



    private DisplayMetrics obtainScreenPixel(Context context) {

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        return outMetrics;
    }

    public void closeMenu() {
        if (isOpen == MenuStatus.OPEN) {
            this.smoothScrollTo(mMenuWidth, 0);
            isOpen = MenuStatus.CLOSE;
        }
    }

    public void openMenu() {
        if (isOpen == MenuStatus.CLOSE) {
            this.smoothScrollTo(0, 0);
            isOpen = MenuStatus.OPEN;
        }
    }


    public void toggleMenu() {

        if (isOpen == MenuStatus.OPEN) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    public MenuStatus getMenuStatus() {
        return isOpen;
    }


    private float dpChange(int value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getResources().getDisplayMetrics());
    }

    private void obtainAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SlidingMenu,
                defStyleAttr, 0);
        mMenuRightPadding = a.getDimensionPixelOffset(R.styleable.
                        SlidingMenu_rightMargin,
                mMenuRightPadding);
        int pos = a.getInt(R.styleable.SlidingMenu_menuPosition, MENU_POS_LEFT);
        switch (pos) {
            case MENU_POS_LEFT:
                position = MenuPosition.LEFT;
                break;
            case MENU_POS_RIGHT:
                position = MenuPosition.RIGHT;
                break;
            case MENU_POS_BOTH:
                position = MenuPosition.BOTH;
                break;
            default:
                break;

        }
        a.recycle();

    }


    public enum MenuStatus {
        OPEN, CLOSE
    }

    private enum MenuPosition {
        LEFT, RIGHT, BOTH

    }

}
