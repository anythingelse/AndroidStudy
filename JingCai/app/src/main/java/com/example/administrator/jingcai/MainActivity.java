package com.example.administrator.jingcai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="http://v.juhe.cn/toutiao/index?type=top&key=e0241337fd91f86c886d1211f59aeaba";
                HttpUtil.sendOkHttpRequest(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseText = response.body().string();
                        Gson gson = new Gson();
                        List<Data> dataList = gson.fromJson(responseText, TouTiaoResponse.class).result.dataList;
                        for (Data data : dataList){
                            String title = data.title;
                            String date = data.date;
                            String authorName = data.authorName;
                            Log.d("TAG","title: " + title);
                            Log.d("TAG","date: " + date);
                            Log.d("TAG","authorName: " + authorName);
                        }
                    }
                });
            }
        });

    }
}
