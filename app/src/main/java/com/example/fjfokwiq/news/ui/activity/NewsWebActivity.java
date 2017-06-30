package com.example.fjfokwiq.news.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.bean.NewsMessage;
import com.example.fjfokwiq.news.ui.base.BaseActivity;
import com.example.fjfokwiq.news.utlis.CommonUtil;

public class NewsWebActivity extends BaseActivity {
    private static final String CLIENT_MESSAGE = "message";

    private WebView web;

    @Override
    protected int setUiLayout() {

        return R.layout.web_layout;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWeb();
        NewsMessage message = (NewsMessage) getIntent().getSerializableExtra(CLIENT_MESSAGE);
        if (message != null) {
            web.loadUrl(message.getLink());
        }
    }

    private void initWeb() {
        web = (WebView) findViewById(R.id.wv_news);
        WebSettings settings = web.getSettings();
        if (CommonUtil.isNetworkAvailable()) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        settings.setDatabaseEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                web.loadUrl(url);
                return true;
            }
        });
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    public static Intent newIntent(Context context, NewsMessage message) {
        Intent intent = new Intent(context, NewsWebActivity.class);
        intent.putExtra(CLIENT_MESSAGE, message);
        return intent;
    }

    @Override
    protected void onDestroy() {
        if (web != null) {
            web.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            web.clearHistory();
            ((ViewGroup) web.getParent()).removeView(web);
            web.destroy();
            web = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
