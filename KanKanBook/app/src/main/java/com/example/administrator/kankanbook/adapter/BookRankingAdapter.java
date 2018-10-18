package com.example.administrator.kankanbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.activity.BookDetailActivity;
import com.example.administrator.kankanbook.db.RankingList;

import java.util.List;

public class BookRankingAdapter extends RecyclerView.Adapter<BookRankingAdapter.ViewHolder> {

    private List<RankingList> mRankingList;
    private Context mContext;

    public BookRankingAdapter(List<RankingList> rankingLists,Context context){
        mRankingList = rankingLists;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_list_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.rankView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                RankingList rankingList = mRankingList.get(position);
                Intent intent = new Intent(mContext, BookDetailActivity.class);
                intent.putExtra("bookId",rankingList.getBookId());
                intent.putExtra("bookTitle",rankingList.getBookTitle());
                intent.putExtra("bookAuthor",rankingList.getBookAuthor());
                intent.putExtra("bookShortIntro",rankingList.getBookShortIntro());
                intent.putExtra("bookMajorCate",rankingList.getBookMajorCate());
                intent.putExtra("bookMinorCate",rankingList.getBookMinorCate());
                intent.putExtra("bookPicUrl",rankingList.getBookPicUrl());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RankingList rankingList = mRankingList.get(position);
        holder.rank_book_ranking.setText(rankingList.getRanking()+"");
        holder.rank_book_minor.setText(rankingList.getBookMinorCate());
        holder.rank_book_content.setText(rankingList.getBookShortIntro());
        holder.rank_book_name.setText(rankingList.getBookTitle());
        holder.rank_book_author.setText(rankingList.getBookAuthor());
        holder.rank_book_major.setText(rankingList.getBookMajorCate());
        Glide.with(mContext).load(rankingList.getBookPicUrl()).into(holder.rank_book_img);
    }

    @Override
    public int getItemCount() {
        return mRankingList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rank_book_name,rank_book_author,rank_book_content,
                rank_book_major,rank_book_minor,rank_book_ranking;
        ImageView rank_book_img;
        View rankView;
        public ViewHolder(View itemView) {
            super(itemView);
            rankView = itemView;
            rank_book_author = itemView.findViewById(R.id.rank_book_author);
            rank_book_name = itemView.findViewById(R.id.rank_book_name);
            rank_book_content = itemView.findViewById(R.id.rank_book_content);
            rank_book_major = itemView.findViewById(R.id.rank_book_major);
            rank_book_minor = itemView.findViewById(R.id.rank_book_minor);
            rank_book_ranking = itemView.findViewById(R.id.rank_book_ranking);
            rank_book_img = itemView.findViewById(R.id.rank_book_img);
        }
    }
}
