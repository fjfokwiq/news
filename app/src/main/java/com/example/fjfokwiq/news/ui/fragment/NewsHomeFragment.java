package com.example.fjfokwiq.news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fjfokwiq.news.R;
import com.example.fjfokwiq.news.api.ApiFactory;
import com.example.fjfokwiq.news.api.NewsApi;
import com.example.fjfokwiq.news.bean.NewsMessage;
import com.example.fjfokwiq.news.bean.NewsRequest;
import com.example.fjfokwiq.news.ui.activity.NewsWebActivity;
import com.example.fjfokwiq.news.ui.adapter.RecyclerContentAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class NewsHomeFragment extends Fragment {
    private RecyclerView news_list;
    private LinearLayoutManager manager;
    private RecyclerContentAdapter mAdapter;
    private List<NewsMessage> newsList;
    private SwipeRefreshLayout fresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(R.layout.news_home_layout, container, false);
        news_list = (RecyclerView) itemView.findViewById(R.id.rv_news);
        fresh = (SwipeRefreshLayout) itemView.findViewById(R.id.layout_fresh);
        return itemView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecycler();
        initFresh();


    }

    private void initFresh() {
        fresh.setColorSchemeColors(R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        fresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews();
                fresh.setRefreshing(false);
            }
        });

    }

    private void initRecycler() {
        manager = new LinearLayoutManager(getContext());
        news_list.setLayoutManager(manager);
        newsList = new ArrayList<>();
        mAdapter = new RecyclerContentAdapter(newsList, getContext());
        news_list.setAdapter(mAdapter);
        loadNews();
        mAdapter.setOnNewsItemListener(new RecyclerContentAdapter.onNewsItemListener() {
            @Override
            public void onNewsItem(int position, NewsMessage message) {
                startActivity(NewsWebActivity.newIntent(getContext(), message));


            }
        });
    }

    public void loadNews() {
        ApiFactory.getNewsService().getNewsJson(buildUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsRequest>() {
                    @Override
                    public void accept(@NonNull NewsRequest newsRequest) throws Exception {
                        newsLoadSucceed(newsRequest, news_list, getContext());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        newsLoadError(throwable, getContext());
                    }
                });
    }

    private Map buildUrl() {
               /* ?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20"*/
        Map<String, Integer> params = new ArrayMap<>();
        params.put("ver", 1);
        params.put("subid", 1);
        params.put("dir", 1);
        params.put("nid", 1);
        params.put("stamp", 20140321);
        params.put("cnt", 20);
        return params;
    }


    public void newsLoadSucceed(NewsRequest newsRequest, RecyclerView news_list, Context context) {
        mAdapter.addData(newsRequest.getData());
    }

    public void newsLoadError(Throwable throwable, Context context) {
        throwable.printStackTrace();
        Toast.makeText(context, "请检查网络", Toast.LENGTH_SHORT).show();
    }


}










