package com.example.fjfokwiq.news.ui.holder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.RecyclerDataModle;
import com.example.fjfokwiq.news.ui.base.BaseHolder;

/**
 * Created by fjfokwiq on 2017/4/21.
 */

public class MenuViewHolder extends BaseHolder<RecyclerDataModle> {
     public ImageView menuIcon;
     public TextView menuName;
     public TextView menuEnglish;
     public CardView card;
     public Context mContext;


    public MenuViewHolder(View itemView) {
        super(itemView);
        menuIcon = (ImageView) itemView.findViewById(R.id.iv_menu_item);
        menuName = (TextView) itemView.findViewById(R.id.tv_menu_name);
        menuEnglish = (TextView) itemView.findViewById(R.id.tv_menu_english);
        card = (CardView) itemView.findViewById(R.id.cv_menu_item);
    }

    @Override
    public void bindHolder(final RecyclerDataModle modle) {
        menuIcon.setImageResource(modle.menuIcon);
        menuName.setText(modle.menuName);
        menuEnglish.setText(modle.menuEnglish);
    }


}
