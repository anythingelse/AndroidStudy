package com.example.administrator.kankanbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
                    Document doc = (Document) Jsoup.connect("http://www.cnblogs.com/").get();

                    Elements elements = doc.select("div.post_item_body");

                    for(Element element : elements){
                        Elements title = element.select("a.titlelnk");
                        Log.e("title:", title.get(0).text());
                        Log.e("url", title.get(0).attr("href"));

                        Elements content = element.select("p.post_item_summary");
                        Log.e("content:", content.get(0).text());

                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        }).start();
    }
}
