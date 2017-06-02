package com.example.fjfokwiq.news.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.NewsMessage;
import com.example.fjfokwiq.news.ui.base.BaseHolder;
import com.example.fjfokwiq.news.ui.holder.ContentViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by fjfokwiq on 2017/5/6.
 */

public class RecyclerContentAdapter extends RecyclerView.Adapter {
    private List<NewsMessage> newsList;
    private Context context;
    private LayoutInflater inflater;
    private onNewsItemListener listener;

    public RecyclerContentAdapter(List<NewsMessage> newsList,Context context) {
        this.newsList = newsList;
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentViewHolder(inflater.inflate(R.layout.recycler_content_layout,parent,false),context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((BaseHolder) holder).bindHolder(newsList.get(position));
        ((ContentViewHolder) holder).card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNewsItem(position,newsList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void addData(List<NewsMessage>newsMessage){
        newsList.addAll(newsMessage);
        notifyDataSetChanged();

    }

    public void setOnNewsItemListener(onNewsItemListener listener){
        this.listener=listener;
    }
    public interface onNewsItemListener{

        void onNewsItem(int position,NewsMessage message);
    }
}
