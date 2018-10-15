package com.example.administrator.jingcai;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.jingcai.db.News;
import com.example.administrator.jingcai.db.NewsTitle;
import com.example.administrator.jingcai.gson.Data;
import com.example.administrator.jingcai.gson.TouTiaoResponse;
import com.example.administrator.jingcai.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.Response;

public class MyFragment extends Fragment {
    private RecyclerView rv_content,rv_title;
    private List<NewsTitle> newsTitleList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        initTitle();
        rv_title = view.findViewById(R.id.view_news_title);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_title.setLayoutManager(linearLayoutManager1);
        NewsTitleAdapter newsTitleAdapter = new NewsTitleAdapter(newsTitleList);
        rv_title.setAdapter(newsTitleAdapter);
        rv_content = view.findViewById(R.id.view_news_content);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_content.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        rv_content.setAdapter(adapter);
        return view;
    }

    public void initTitle(){
        NewsTitle newsTitle = new NewsTitle();
        newsTitle.setTitle("热点");
        newsTitleList.add(newsTitle);
        newsTitle.setTitle("社会");
        newsTitleList.add(newsTitle);
        newsTitle.setTitle("国际");
        newsTitleList.add(newsTitle);
        newsTitle.setTitle("国内");
        newsTitleList.add(newsTitle);

    }

    public List<News> getNews(){
        final List<News> newsList = new ArrayList<>();
        String url ="http://v.juhe.cn/toutiao/index?type=top&key=e0241337fd91f86c886d1211f59aeaba";
        HttpUtil.sendOkHttpRequest(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Gson gson = new Gson();
                List<Data> dataList = gson.fromJson(responseText, TouTiaoResponse.class).result.dataList;
                for (Data data : dataList){
                    News news = new News();
                    news.setAuthorName(data.authorName);
                    news.setDate(data.date);
                    news.setTitle(data.title);
                    news.setPicUrl1(data.picUrl1);
                    news.setPicUrl2(data.picUrl2);
                    news.setPicUrl3(data.picUrl3);
                    news.setUrl(data.url);
                    newsList.add(news);
                }

            }
        });
        return newsList;
    }
}
