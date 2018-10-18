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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.Util.HttpUtil;
import com.example.administrator.kankanbook.Util.Utility;
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


    private EditText search_et;
    private ImageView search_iv;
    private ImageView list_more;
    private RelativeLayout layout_hot,layout_search,layout_new;
    private ImageView hot_more,search_more,new_more;
    private RecyclerView rv_book_ranking;
    private ProgressDialog progressDialog;

    private List<RankingList> rankingLists = new ArrayList<>();
    private List<RankingList> dataList = new ArrayList<>();
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

        layout_hot.setOnClickListener(this);
        layout_new.setOnClickListener(this);
        layout_search.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_book_ranking.setLayoutManager(layoutManager);
        adapter = new BookRankingAdapter(dataList,getActivity());
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
        layout_hot.setBackgroundColor(getActivity().getResources().getColor(R.color.colorHot));
        layout_search.setBackgroundColor(getActivity().getResources().getColor(R.color.colorCool));
        layout_new.setBackgroundColor(getActivity().getResources().getColor(R.color.colorCool));
        rankingLists = DataSupport.where("bookStyle = ?","0").find(RankingList.class);
        if (rankingLists.size() > 0){
            dataList.clear();
            dataList.addAll(rankingLists);
            adapter.notifyDataSetChanged();
        }
    }
    @SuppressLint("ResourceAsColor")
    public void querySearchList(){
        layout_hot.setBackgroundColor(getActivity().getResources().getColor(R.color.colorCool));
        layout_search.setBackgroundColor(getActivity().getResources().getColor(R.color.colorHot));
        layout_new.setBackgroundColor(getActivity().getResources().getColor(R.color.colorCool));
        rankingLists = DataSupport.where("bookStyle = ?","1").find(RankingList.class);
        if (rankingLists.size() > 0){
            dataList.clear();
            dataList.addAll(rankingLists);
            adapter.notifyDataSetChanged();
        }
    }
    @SuppressLint("ResourceAsColor")
    public void queryNewList(){
        layout_hot.setBackgroundColor(getActivity().getResources().getColor(R.color.colorCool));
        layout_search.setBackgroundColor(getActivity().getResources().getColor(R.color.colorCool));
        layout_new.setBackgroundColor(getActivity().getResources().getColor(R.color.colorHot));
        rankingLists = DataSupport.where("bookStyle = ?","2").find(RankingList.class);
        if (rankingLists.size() > 0){
            dataList.clear();
            dataList.addAll(rankingLists);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_hot:
                queryHotList();break;
            case R.id.layout_search:
                querySearchList();break;
            case R.id.layout_new:
                queryNewList();break;
        }
    }

}
