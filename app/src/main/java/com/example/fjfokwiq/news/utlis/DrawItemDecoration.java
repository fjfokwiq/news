package com.example.fjfokwiq.news.utlis;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class DrawItemDecoration extends RecyclerView.ItemDecoration {
      /*绘制recyclerView分隔线*/

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = 10;
        super.getItemOffsets(outRect, view, parent, state);
        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int spanCount = params.getSpanSize();
        int spanIndex = params.getSpanIndex();
        if (spanCount != ((GridLayoutManager) parent.getLayoutManager()).getSpanCount()) {
            if (spanIndex != 0) {
                outRect.left = 3;
            }
        }

    }
}
