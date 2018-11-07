package com.example.administrator.kankanbook.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.Util.HttpUtil;
import com.example.administrator.kankanbook.adapter.BookRankingAdapter;
import com.example.administrator.kankanbook.db.RankingList;
import com.example.administrator.kankanbook.gson.BookSearchs;
import com.example.administrator.kankanbook.gson.Ranking;
import com.example.administrator.kankanbook.gson.SearchBook;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {
    private ImageView back_search,iv_next;
    private RecyclerView rv_search;
    private EditText et_search;
    private String url_ = "http://novel.juhe.im/search?keyword=";
    private String keyword;
    private BookRankingAdapter adapter;
    private List<RankingList> dataList = new ArrayList<>();
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getData();
        initView();
    }


    public void initView(){
        back_search = findViewById(R.id.back_search);
        et_search = findViewById(R.id.et_search);
        iv_next = findViewById(R.id.iv_next);
        progressBar = findViewById(R.id.search_progress);
        rv_search = findViewById(R.id.rv_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_search.setLayoutManager(layoutManager);
        adapter = new BookRankingAdapter(dataList,this);
        rv_search.setAdapter(adapter);
        if (keyword != null) {
            initRecyclerView();
        }

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyword = et_search.getText().toString();
                if (keyword != null){
                    initRecyclerView();
                }
            }
        });
    }

    public void initRecyclerView(){
        progressBar.setVisibility(View.VISIBLE);
        String url = url_ + keyword;
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                List<SearchBook> lists = gson.fromJson(response.body().string(),BookSearchs.class).searchBookList;
                dataList.clear();
                for (SearchBook book : lists){
                    RankingList rankingList = new RankingList();
                    rankingList.setBookAuthor(book.bookAuthor);
                    rankingList.setBookId(book.bookId);
                    rankingList.setBookMajorCate(book.bookCat);
                    rankingList.setBookShortIntro(book.bookShortIntro);
                    rankingList.setBookTitle(book.bookTitle);
                    String picUrl = TextUtils.substring(URLDecoder.decode(book.bookPicUrl,"UTF-8"),
                            7,URLDecoder.decode(book.bookPicUrl,"UTF-8").length());
                    rankingList.setBookPicUrl(picUrl);
                    dataList.add(rankingList);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    public void getData() {
        Intent intent = getIntent();
        keyword = intent.getStringExtra("keyword");
    }
}
