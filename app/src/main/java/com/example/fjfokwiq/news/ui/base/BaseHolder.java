package com.example.fjfokwiq.news.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.fjfokwiq.news.bean.RecyclerDataModle;


public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(T modle, Context context);

}
