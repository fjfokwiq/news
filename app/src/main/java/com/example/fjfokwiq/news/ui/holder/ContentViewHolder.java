package com.example.fjfokwiq.news.ui.holder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.NewsMessage;
import com.example.fjfokwiq.news.bean.RecyclerDataModle;
import com.example.fjfokwiq.news.ui.activity.NewsWebActivity;
import com.example.fjfokwiq.news.ui.base.BaseHolder;



public class ContentViewHolder extends BaseHolder<NewsMessage> {
    public CardView card;
    public ImageView newsImg;
    public TextView newsTitle;
    public TextView newsContent;
    public Context mContext;
    public ContentViewHolder(View itemView,Context context) {
        super(itemView);
        this.mContext=context;
        card = (CardView) itemView.findViewById(R.id.cv_content_item);
        newsImg = (ImageView) itemView.findViewById(R.id.iv_news);
        newsTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
        newsContent = (TextView) itemView.findViewById(R.id.tv_news_content);

    }

    @Override
    public void bindHolder(final NewsMessage modle) {

      Glide.with(mContext)
              .load(modle.getIcon())
              .skipMemoryCache(false)
              .diskCacheStrategy(DiskCacheStrategy.SOURCE)
              .placeholder(R.drawable.defaultpic)
              .error(R.drawable.sorry)
              .into(newsImg);
        newsTitle.setText(modle.getTitle());
        newsContent.setText(modle.getSummary());
    }
}
