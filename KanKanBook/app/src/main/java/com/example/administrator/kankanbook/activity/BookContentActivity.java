package com.example.administrator.kankanbook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.Util.HttpUtil;
import com.example.administrator.kankanbook.db.ChapterList;
import com.example.administrator.kankanbook.gson.BookContents;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BookContentActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private ImageView back;
    private TextView tv_title;
    private TextView tv_content;
    private String bookId,bookTitle,bookLink;
    private int bookOrder;
    private ProgressBar progressBar;
    private String url_ = "http://novel.juhe.im/chapters/";
    GestureDetector detector;
    private List<ChapterList> chapterLists = new ArrayList<>();
    private List<ChapterList> lists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_content);
        detector = new GestureDetector(this,this);
        getData();
        initView();
        initContent();

    }

    public void initView(){
        back = findViewById(R.id.title_back);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.content_text);
        progressBar = findViewById(R.id.progress);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_title.setText(bookTitle);
        lists = DataSupport.where("bookId = ?",bookId).find(ChapterList.class);
        if (lists.size() > 0){
            chapterLists.clear();
            chapterLists.addAll(lists);
        }else Toast.makeText(this,"未找到章节列表",Toast.LENGTH_SHORT).show();

    }

    public void initContent(){
        progressBar.setVisibility(View.VISIBLE);
        try {
            String url = url_ + URLEncoder.encode(bookLink,"UTF-8");
            HttpUtil.sendOkHttpRequest(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Gson gson = new Gson();
                    final String content = gson.fromJson(response.body().string(),
                            BookContents.class).contents.cpContent;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_content.setText(content);
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void getData(){
        Intent intent = getIntent();
        bookId = intent.getStringExtra("bookId");
        bookTitle = intent.getStringExtra("title");
        bookLink = intent.getStringExtra("link");
        bookOrder = intent.getIntExtra("order",1);
        Log.d("TAG","order: " + bookOrder);
        Log.d("TAG","bookId: " + bookId);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (motionEvent.getX() - motionEvent1.getX() > 50){
            //左划
            Toast.makeText(this,"左滑",Toast.LENGTH_SHORT).show();
            Log.d("TAG","move: " + (motionEvent.getX() - motionEvent1.getX()));
            bookOrder++;
            tv_content.setText("");
            bookTitle = chapterLists.get(bookOrder-1).title;
            bookLink = chapterLists.get(bookOrder-1).link;
            tv_title.setText(bookTitle);
            initContent();
            return true;
        }else if (motionEvent1.getX() - motionEvent.getX() > 50 ){
            //右划
            Toast.makeText(this,"右划",Toast.LENGTH_SHORT).show();
            Log.d("TAG","move2: " + (motionEvent1.getX() - motionEvent.getX()));
            bookOrder--;
            tv_content.setText("");
            bookTitle = chapterLists.get(bookOrder-1).title;
            bookLink = chapterLists.get(bookOrder-1).link;
            tv_title.setText(bookTitle);
            initContent();
            return true;
        }
        return false;
    }
}
