package com.example.administrator.jingcai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.jingcai.db.News;
import com.example.administrator.jingcai.gson.Data;
import com.example.administrator.jingcai.gson.Result;
import com.example.administrator.jingcai.gson.TouTiaoResponse;
import com.example.administrator.jingcai.util.HttpUtil;
import com.example.administrator.jingcai.util.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private List<News> newsList = new ArrayList<>();
    private Button btn_news,btn_joke,btn_dream;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        btn_dream = findViewById(R.id.btn_dream);
        btn_joke = findViewById(R.id.btn_joke);
        btn_news = findViewById(R.id.btn_news);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("新闻");
        btn_news.setOnClickListener(this);
        btn_joke.setOnClickListener(this);
        btn_dream.setOnClickListener(this);
    }
    public void getNews(){
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
                    Log.d("TAG","title2:" + data.title);
                    newsList.add(news);

                }
                Log.d("TAG","listSize:"+newsList.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerView = findViewById(R.id.rv);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        NewsAdapter adapter = new NewsAdapter(newsList);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
