package com.example.administrator.kankanbook.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.Util.HttpUtil;
import com.example.administrator.kankanbook.adapter.BookRankingAdapter;
import com.example.administrator.kankanbook.db.RankingList;
import com.example.administrator.kankanbook.gson.BookRankingDetails;
import com.example.administrator.kankanbook.gson.Books;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class BookStoreFragment extends Fragment implements View.OnClickListener{
    private String HotIdUrl = "http://novel.juhe.im/rank/54d43437d47d13ff21cad58b";
    private String SearchIdUrl = "http://novel.juhe.im/rank/5a684515fc84c2b8efaa9875";
    private String NewIdUrl = "http://novel.juhe.im/rank/5a39d453fc84c2b8ef885812";

    private EditText search_et;
    private ImageView search_iv;
    private ImageView list_more;
    private LinearLayout layout_hot,layout_search,layout_new;
    private ImageView hot_more,search_more,new_more;
    private RecyclerView rv_book_ranking;
    private ProgressDialog progressDialog;

    private List<RankingList> rankingLists = new ArrayList<>();
    private BookRankingAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookstore,container,false);
        search_et = view.findViewById(R.id.search_et);
        search_iv = view.findViewById(R.id.search_iv);
        list_more = view.findViewById(R.id.list_more);
        layout_hot = view.findViewById(R.id.layout_hot);
        layout_search = view.findViewById(R.id.layout_search);
        layout_new = view.findViewById(R.id.layout_new);
        hot_more = view.findViewById(R.id.hot_more);
        search_more = view.findViewById(R.id.search_more);
        new_more = view.findViewById(R.id.new_more);
        rv_book_ranking = view.findViewById(R.id.rv_book_ranking);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        Log.d("TAG","name: "+getActivity().getResources());
        rv_book_ranking.setLayoutManager(layoutManager);
        adapter = new BookRankingAdapter(rankingLists);
        rv_book_ranking.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        queryHotList();
    }

    @SuppressLint("ResourceAsColor")
    public void queryHotList(){
        layout_hot.setBackgroundColor(R.color.colorHot);
        layout_search.setBackgroundColor(R.color.colorCool);
        layout_new.setBackgroundColor(R.color.colorCool);
        rankingLists = DataSupport.findAll(RankingList.class);
        if (rankingLists.size() > 0){
            adapter.notifyDataSetChanged();
        }else {
            getBookRankingList(HotIdUrl);
        }
    }

    public void getBookRankingList(String url){
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG","e : " + e.getMessage());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Gson gson = new Gson();
                List<Books> booksList = gson.fromJson(responseText, BookRankingDetails.class).ranking.books;
                int t = 1;
                rankingLists.clear();
                for (Books books : booksList) {
                    RankingList rankingList = new RankingList();
                    rankingList.setBookAuthor(books.bookAuthor);
                    rankingList.setBookId(books.bookId);
                    rankingList.setBookMajorCate(books.bookMajorCate);
                    rankingList.setBookMinorCate(books.bookMinorCate);
                    rankingList.setBookShortIntro(books.bookShortIntro);
                    rankingList.setBookTitle(books.bookTitle);
                    rankingList.setRanking(t);

                    if (t <= 20) {
                        rankingLists.add(rankingList);
                    }
                    rankingList.save();
                    t++;
                }
                closeProgressDialog();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        Log.d("TAG","size:" + rankingLists.size());
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
    /**
     * 显示进度对话框
     */
    private void showProgressDialog(){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }
    /**
     * 关闭进度对话框
     */
    private void closeProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
