package com.example.administrator.kankanbook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.Util.HttpUtil;
import com.example.administrator.kankanbook.adapter.BookChapterAdapter;
import com.example.administrator.kankanbook.db.BookInfo;
import com.example.administrator.kankanbook.db.BookSource;
import com.example.administrator.kankanbook.db.ChapterList;
import com.example.administrator.kankanbook.gson.BookChapters;
import com.example.administrator.kankanbook.gson.BookInfoData;
import com.example.administrator.kankanbook.gson.BookSourceData;
import com.example.administrator.kankanbook.gson.Chapters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class BookDetailActivity extends AppCompatActivity {
    private TextView detail_book_name,detail_book_author,detail_book_wordcount,
            detail_book_major,detail_book_minor,detail_book_score,
            detail_book_chapterNew,detail_book_content;
    private ImageView detail_book_img;
    private Button detail_run_btn,detail_read_btn;
    private String bookName,bookAuthor,bookMajor,bookMinor,booklastChapter,bookContent;
    private int bookWordCount;
    private String bookScore;
    private String picUrl;
    private String bookId;
    private String sourceId = null;
    private RecyclerView rv_book_chapter;
    private List<ChapterList> chapterLists = new ArrayList<>();
    BookChapterAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        getData();
        initView();
    }
    public void initView(){
        detail_book_name = findViewById(R.id.detail_book_name);
        detail_book_author = findViewById(R.id.detail_book_author);
        detail_book_wordcount = findViewById(R.id.detail_book_wordcount);
        detail_book_major = findViewById(R.id.detail_book_major);
        detail_book_minor = findViewById(R.id.detail_book_minor);
        detail_book_score = findViewById(R.id.detail_book_score);
        detail_book_chapterNew = findViewById(R.id.detail_book_chapterNew);
        detail_book_content = findViewById(R.id.detail_book_content);
        detail_book_img = findViewById(R.id.detail_book_img);
        detail_run_btn = findViewById(R.id.detail_run_btn);
        detail_read_btn = findViewById(R.id.detail_read_btn);
        rv_book_chapter = findViewById(R.id.rv_book_chapter);


        detail_book_name.setText(bookName);
        detail_book_author.setText(bookAuthor);
        detail_book_major.setText(bookMajor);
        detail_book_minor.setText(bookMinor);
        detail_book_content.setText(bookContent);
        Glide.with(this).load(picUrl).into(detail_book_img);

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        rv_book_chapter.setLayoutManager(manager);
        adapter = new BookChapterAdapter(chapterLists);
        rv_book_chapter.setAdapter(adapter);

        List<BookSource> bookSources = DataSupport.where("bookId = ?",bookId).find(BookSource.class);
        if (bookSources.size() > 0){
            sourceId = bookSources.get(0).sourceId;
            Log.d("TAG","sourceId1:"+sourceId);
        }else getSourceId();

        if (sourceId!=null){
            initRecyclerView();
        }

        List<BookInfo> bookInfoList = DataSupport.where("bookId = ?",bookId).find(BookInfo.class);
        if (bookInfoList.size() > 0){
            detail_book_chapterNew.setText(bookInfoList.get(0).getLastChapter());
            detail_book_score.setText(""+bookInfoList.get(0).getScore());
            detail_book_wordcount.setText(""+bookInfoList.get(0).getWordCount());
            initView2();
        }else initView2();


    }



    public void initRecyclerView(){

        List<ChapterList> lists = DataSupport.where("bookId = ?",bookId).find(ChapterList.class);
        if (lists.size() > 0){
            chapterLists.clear();
            chapterLists.addAll(lists);
            adapter.notifyDataSetChanged();
            initView3();
        }else initView3();
    }

    public void initView2(){
        String bookInfoUrl = "http://novel.juhe.im/book-info/" + bookId;
        HttpUtil.sendOkHttpRequest(bookInfoUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                BookInfoData data = gson.fromJson(response.body().string(), BookInfoData.class);

                bookScore = TextUtils.substring((Double.toString(data.rating.score)),0,3);
                booklastChapter = data.lastChapter;
                bookWordCount = data.wordCount;
                BookInfo bookInfo = new BookInfo();
                bookInfo.setBookId(bookId);
                bookInfo.setLastChapter(booklastChapter);
                bookInfo.setScore(bookScore);
                bookInfo.setWordCount(bookWordCount);
                bookInfo.save();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        detail_book_chapterNew.setText(booklastChapter);
                        detail_book_score.setText(bookScore);
                        detail_book_wordcount.setText(""+bookWordCount);
                    }
                });
            }
        });
    }

    public void initView3(){
        String url = "http://novel.juhe.im/book-chapters/"+sourceId;
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                List<Chapters> chaptersList = gson.fromJson(response.body().string(),
                        BookChapters.class).chapters;
                for (Chapters chapters : chaptersList){
                    ChapterList chapterList = new ChapterList();
                    chapterList.setBookId(bookId);
                    chapterList.setLink(chapters.link);
                    chapterList.setOrder(chapters.order);
                    chapterList.setTitle(chapters.title);
                    chapterList.save();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initRecyclerView();
                    }
                });
            }
        });
    }

    public void getSourceId(){
        String url = "http://novel.juhe.im/book-sources?view=summary&book="+bookId;
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                List<BookSourceData> bookSourceDataList = gson.fromJson(response.body().string(),
                        new TypeToken<List<BookSourceData>>(){}.getType());
                for (BookSourceData bookSourceData : bookSourceDataList){
                    BookSource bookSource = new BookSource();
                    bookSource.setBookId(bookId);
                    bookSource.setSourceId(bookSourceData.sourceId);
                    bookSource.setSourceName(bookSourceData.sourceName);
                    Log.d("TAG","sourceId2:"+bookSourceData.sourceId);
                    bookSource.save();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<BookSource> bookSources = DataSupport.where("bookId = ?",bookId).find(BookSource.class);
                            sourceId = bookSources.get(0).sourceId;
                        Log.d("TAG","sourceId3:"+sourceId);
                    }
                });
            }
        });
    }

    public void getData(){
        Intent intent = getIntent();
        bookId = intent.getStringExtra("bookId");
        bookName = intent.getStringExtra("bookTitle");
        bookAuthor = intent.getStringExtra("bookAuthor");
        bookMajor = intent.getStringExtra("bookMajorCate");
        bookMinor = intent.getStringExtra("bookMinorCate");
        bookContent = intent.getStringExtra("bookShortIntro");
        picUrl = intent.getStringExtra("bookPicUrl");
        Log.d("TAG",bookId+","+bookName);
    }
}
