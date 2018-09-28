package com.example.administrator.kankanbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder().url("https://read.qidian.com/chapter/4uzLQ9PRQm3-JlVC31J8Aw2/X8BJnTZbhJ3wrjbX3WA1AA2").build();
//                    Response response = client.newCall(request).execute();
//                    final String responseData = response.body().string();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textView.setText(responseData);
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://www.17k.com/chapter/108821/3148523.html").build();
                    Response response = client.newCall(request).execute();
                    final String responseData = response.body().string();
//                    Document doc =  Jsoup.connect("http://www.17k.com/chapter/108821/3148523.html").get();
                    String temp = responseData.replaceAll("<br />", "\n");
                    Document doc = Jsoup.parse(temp);
                    Log.e("TAG","html:" + temp);
                    Elements elements = doc.select("div.p");
                    final String content = elements.text();
                    final String text =Jsoup.clean(temp, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
                    Log.e("TAG","content: " + text);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(content);
                        }
                    });

                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        }).start();
    }
}
