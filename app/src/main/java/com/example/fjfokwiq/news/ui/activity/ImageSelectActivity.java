package com.example.fjfokwiq.news.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.ImageMessage;
import com.example.fjfokwiq.news.ui.adapter.RecyclerImageAdapter;
import com.example.fjfokwiq.news.ui.base.BaseActivity;
import com.example.fjfokwiq.news.utlis.DrawItemDecoration;
import com.example.fjfokwiq.news.utlis.ImageScanner;

import java.util.ArrayList;
import java.util.List;


public class ImageSelectActivity extends BaseActivity {
    private RecyclerView image;
    private RecyclerImageAdapter adapter;
    private List<ImageMessage> adapterMessage = new ArrayList<>();
    private List<String> imageCount = new ArrayList<>();
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initShow();
        startScanner();
        initEvent();

    }

    /*点击处理*/
    private void initEvent() {
        updateButton();
        adapter.setOnBoxCheckListener(new RecyclerImageAdapter.onBoxCheckListener() {
            @Override
            public void onCheck(ImageMessage message) {
                    imageCount.clear();
                    imageCount.add(message.getPath());
                updateButton();
            }
        });
        adapter.setOnClickPreviewListener(new RecyclerImageAdapter.onClickPreviewListener() {
            @Override
            public void onClickPreview(int position) {
                /*
                * 取得扫描出来的部分图片
                * */
                List<ImageMessage> messages = adapter.getMessage();
                List<String> imagePath = new ArrayList<>();
                int startCount;
                int endCount;
                if (position < 50) {
                    startCount = position;
                    endCount = position + 70;

                } else {
                    startCount = position - 50;
                    endCount = position + 50;
                }
                for (int i = startCount; i < endCount; i++) {
                    imagePath.add(messages.get(i).getPath());

                }

                startActivity(ImagePreviewActivity.newsIntent(ImageSelectActivity.this, position, imagePath));
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageCount.size() > 0) {
                    String imageString = imageCount.get(0);
                    Intent intent = new Intent();
                    intent.putExtra("ok", imageString);
                    setResult(RESULT_OK, intent);
                }

                finish();
            }
        });

    }

    private void updateButton() {
        ok.setText("确定" + imageCount.size() + "/1");
        if (imageCount.size() != 1) {
            ok.setAlpha(0.5f);
            ok.setClickable(false);
        } else {
            ok.setAlpha(1.0f);
            ok.setClickable(true);
        }
    }


    /*开始扫描手机内图片*/
    private void startScanner() {
            ImageScanner.getInstance().scanner(new ImageScanner.onScannerSucceed() {
                @Override
                public void onSucceed(List<ImageMessage> message) {
                    adapter.addData(message);
                }
            });

    }

    /*加载控件*/
    private void initShow() {
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        adapter = new RecyclerImageAdapter(this, adapterMessage, image);
        image.addItemDecoration(new DrawItemDecoration());
        image.setLayoutManager(manager);
        image.setAdapter(adapter);
    }

    /*初始化控件*/
    private void initView() {
        image = (RecyclerView) findViewById(R.id.rv_file_image);
        ok = (Button) findViewById(R.id.bt_ok);
    }

    @Override
    protected int setUiLayout() {
        return R.layout.activity_image_select;
    }


}
