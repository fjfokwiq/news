package com.example.fjfokwiq.news.ui.holder;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.NewsMessage;
import com.example.fjfokwiq.news.ui.base.BaseHolder;



public class ContentViewHolder extends BaseHolder<NewsMessage> {
    public CardView card;
    public ImageView newsImg;
    public TextView newsTitle;
    public TextView newsContent;
    private SparseArrayCompat<View> views = new SparseArrayCompat<>();
    public ContentViewHolder(View itemView) {
        super(itemView);
        card = getView(itemView,R.id.cv_content_item);
        newsImg = getView(itemView,R.id.iv_news);
        newsTitle = getView(itemView,R.id.tv_news_title);
        newsContent = getView(itemView,R.id.tv_news_content);

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
    public void bindHolder(final NewsMessage module, Context context) {

      Glide.with(context)
              .load(module.getIcon())
              .skipMemoryCache(false)
              .diskCacheStrategy(DiskCacheStrategy.SOURCE)
              .placeholder(R.drawable.defaultpic)
              .error(R.drawable.sorry)
              .into(newsImg);
        newsTitle.setText(module.getTitle());
        newsContent.setText(module.getSummary());
    }
}
