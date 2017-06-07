package com.example.fjfokwiq.news.ui.holder;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.RecyclerDataModle;
import com.example.fjfokwiq.news.ui.base.BaseHolder;


public class MenuViewHolder extends BaseHolder<RecyclerDataModle> {
     public ImageView menuIcon;
     public TextView menuName;
     public TextView menuEnglish;
     public CardView card;
    private SparseArrayCompat<View> views = new SparseArrayCompat<>();

    public MenuViewHolder(View itemView) {
        super(itemView);
        menuIcon = getView(itemView,R.id.iv_menu_item);
        menuName = getView(itemView,R.id.tv_menu_name);
        menuEnglish = getView(itemView,R.id.tv_menu_english);
        card = getView(itemView,R.id.cv_menu_item);
    }
    private <T extends View>T getView(View itemView, @IdRes int id){
        View view;
        if (views.get(id) != null) {
            view = views.get(id);
        } else {
            view = itemView.findViewById(id);
            views.put(id,view);
        }
        return ((T) view);
    }
    @Override
    public void bindHolder(final RecyclerDataModle modle,Context context) {
        menuIcon.setImageResource(modle.menuIcon);
        menuName.setText(modle.menuName);
        menuEnglish.setText(modle.menuEnglish);
    }


}
