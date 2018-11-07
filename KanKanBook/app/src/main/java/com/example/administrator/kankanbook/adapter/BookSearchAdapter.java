package com.example.administrator.kankanbook.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.db.RankingList;

import java.util.List;

public class BookSearchAdapter extends RecyclerView.Adapter<BookSearchAdapter.ViewHolder> {
    private List<RankingList> mRankingList;
    private Context mContext;

    public BookSearchAdapter(List<RankingList> rankingLists,Context context){
        mRankingList = rankingLists;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mRankingList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rank_book_name,rank_book_author,rank_book_content,
                rank_book_major;
        ImageView rank_book_img;
        View searchView;
        public ViewHolder(View itemView) {
            super(itemView);
            searchView = itemView;
            rank_book_author = itemView.findViewById(R.id.rank_book_author);
            rank_book_name = itemView.findViewById(R.id.rank_book_name);
            rank_book_content = itemView.findViewById(R.id.rank_book_content);
            rank_book_major = itemView.findViewById(R.id.rank_book_major);
            rank_book_img = itemView.findViewById(R.id.rank_book_img);
        }
    }
}
