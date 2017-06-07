package com.example.fjfokwiq.news.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.RecyclerDataModle;
import com.example.fjfokwiq.news.ui.holder.MenuViewHolder;
import com.example.fjfokwiq.news.ui.base.BaseHolder;

import java.util.List;

/**
 * Created by fjfokwiq on 2017/4/21.
 */

public class RecyclerMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private List<RecyclerDataModle> mList;
    private Context mContext;
    private onMenuItemListener listener;

    public void addData(List<RecyclerDataModle> mList) {
        this.mList = mList;
    }

    public RecyclerMenuAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MenuViewHolder(inflater.inflate(R.layout.recycler_menu_layout, parent, false));

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((BaseHolder) holder).bindHolder(mList.get(position),mContext);
        ((MenuViewHolder) holder).card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMenuItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnMenuItemListener(onMenuItemListener listener) {
        this.listener = listener;
    }

    public interface onMenuItemListener {
        void onMenuItem(int position);
    }
}
