package com.example.administrator.kankanbook.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.db.RankingList;

import java.util.List;

public class BookRankingAdapter extends RecyclerView.Adapter<BookRankingAdapter.ViewHolder> {

    private List<RankingList> mRankingList;

    public BookRankingAdapter(List<RankingList> rankingLists){
        mRankingList = rankingLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rank_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RankingList rankingList = mRankingList.get(position);
        if (position==0){
            holder.rank_book_ranking.setTextColor(Color.RED);
        }else if (position==1){
            holder.rank_book_ranking.setTextColor(Color.YELLOW);
        }else if (position==2){
            holder.rank_book_ranking.setTextColor(Color.GREEN);
        }
        holder.rank_book_ranking.setText(rankingList.getRanking()+"");
        holder.rank_book_minor.setText(rankingList.getBookMinorCate());
        holder.rank_book_content.setText(rankingList.getBookShortIntro());
        holder.rank_book_name.setText(rankingList.getBookTitle());
        holder.rank_book_author.setText(rankingList.getBookAuthor());
        holder.rank_book_major.setText(rankingList.getBookMajorCate());
    }

    @Override
    public int getItemCount() {
        return mRankingList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rank_book_name,rank_book_author,rank_book_content,
                rank_book_major,rank_book_minor,rank_book_ranking;
        public ViewHolder(View itemView) {
            super(itemView);
            rank_book_author = itemView.findViewById(R.id.rank_book_author);
            rank_book_name = itemView.findViewById(R.id.rank_book_name);
            rank_book_content = itemView.findViewById(R.id.rank_book_content);
            rank_book_major = itemView.findViewById(R.id.rank_book_major);
            rank_book_minor = itemView.findViewById(R.id.rank_book_minor);
            rank_book_ranking = itemView.findViewById(R.id.rank_book_ranking);
        }
    }
}
