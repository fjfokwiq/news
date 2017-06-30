package com.example.fjfokwiq.news.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.ImageMessage;
import com.example.fjfokwiq.news.ui.base.BaseHolder;
import com.example.fjfokwiq.news.ui.holder.ImageViewHolder;

import java.util.List;


public class RecyclerImageAdapter extends RecyclerView.Adapter {
    private Context context;
    private LayoutInflater inflater;
    private List<ImageMessage> messages;
    private onBoxCheckListener boxListener;
    private onClickPreviewListener previewListener;
    private int selectPos;
    private RecyclerView rv;

    public RecyclerImageAdapter(Context context, List<ImageMessage> messages, RecyclerView view) {
        inflater = LayoutInflater.from(context);
        this.rv = view;
        this.context = context;
        this.messages = messages;
    }

    /*添加数据*/
    public void addData(List<ImageMessage> mData) {
        messages.addAll(mData);
        refresh();
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageViewHolder holder = new ImageViewHolder(inflater.inflate(R.layout.recycler_select_layout, parent, false));

        return holder;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((BaseHolder) holder).bindHolder(messages.get(position), context);
       // 选中监听
        ((ImageViewHolder) holder).getCheck().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageViewHolder currentHolder = (ImageViewHolder) rv.findViewHolderForLayoutPosition(selectPos);
                if (currentHolder != null) {
                    Glide.with(context).load(R.drawable.icon_image_un_select).into(currentHolder.getCheck());
                    messages.get(selectPos).setSelect(false);
                }
                selectPos=position;
                messages.get(selectPos).setSelect(true);
                currentHolder = (ImageViewHolder) rv.findViewHolderForLayoutPosition(selectPos);
                Glide.with(context).load(R.drawable.icon_image_select).into(currentHolder.getCheck());
                boxListener.onCheck(messages.get(position));
            }
        });


        /*点击图片预览监听*/
        ((ImageViewHolder) holder).getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewListener.onClickPreview(position);
            }
        });
    }

    public List<ImageMessage> getMessage() {
        return messages;
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }


    /*监听回调*/
    public void setOnBoxCheckListener(onBoxCheckListener listener) {
        this.boxListener = listener;
    }

    public void setOnClickPreviewListener(onClickPreviewListener listener) {
        this.previewListener = listener;
    }

    public interface onBoxCheckListener {
        void onCheck(ImageMessage message);
    }

    public interface onClickPreviewListener {
        void onClickPreview(int position);
    }

}
