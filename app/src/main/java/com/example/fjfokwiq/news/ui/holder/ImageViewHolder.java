package com.example.fjfokwiq.news.ui.holder;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.ImageMessage;
import com.example.fjfokwiq.news.ui.base.BaseHolder;

import java.io.File;


public class ImageViewHolder extends BaseHolder<ImageMessage> {
    private AppCompatImageView image;
    private AppCompatImageView check;
    private AppCompatTextView name;
    private SparseArrayCompat<View> views = new SparseArrayCompat<>();

    public ImageViewHolder(View itemView) {
        super(itemView);
        image = getView(itemView,R.id.iv_file_img);
        check = getView(itemView,R.id.cb_box);
        name = getView(itemView,R.id.tv_image_name);
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
    public ImageView getImageView(){
        return image;
    }
    public ImageView getCheck(){
        return check;
    }

    @Override
    public void bindHolder(ImageMessage module, Context context) {
        Glide.with(context).load(new File(module.getPath())).into(image);
        name.setText(module.getName());
    }

}
