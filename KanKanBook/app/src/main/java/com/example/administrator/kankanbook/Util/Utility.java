package com.example.administrator.kankanbook.Util;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.kankanbook.db.RankingList;
import com.example.administrator.kankanbook.gson.BookRankingDetails;
import com.example.administrator.kankanbook.gson.Books;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Utility {

    public  static void getBookRankingList(String url,final int style){
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG","e : " + e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Gson gson = new Gson();
                List<Books> booksList = gson.fromJson(responseText, BookRankingDetails.class).ranking.books;
                int t = 1;
                for (Books books : booksList) {
                    RankingList rankingList = new RankingList();
                    rankingList.setBookAuthor(books.bookAuthor);
                    rankingList.setBookId(books.bookId);
                    rankingList.setBookMajorCate(books.bookMajorCate);
                    rankingList.setBookMinorCate(books.bookMinorCate);
                    rankingList.setBookShortIntro(books.bookShortIntro);
                    rankingList.setBookTitle(books.bookTitle);
                    String picUrl = TextUtils.substring(URLDecoder.decode(books.bookPicUrl,"UTF-8"),
                            7,URLDecoder.decode(books.bookPicUrl,"UTF-8").length());
                    rankingList.setBookPicUrl(picUrl);
                    rankingList.setRanking(t);
                    switch (style){
                        case RankingList.STYLE_HOT:rankingList.setBookStyle(RankingList.STYLE_HOT);break;
                        case RankingList.STYLE_SEARCH:rankingList.setBookStyle(RankingList.STYLE_SEARCH);break;
                        case RankingList.STYLE_NEW:rankingList.setBookStyle(RankingList.STYLE_NEW);break;
                        default:break;
                    }

                    if (t <= 30) {
                        rankingList.save();
                    }
                    t++;
                }

            }
        });
    }

}
