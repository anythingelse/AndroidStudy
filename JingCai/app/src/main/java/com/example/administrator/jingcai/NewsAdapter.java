package com.example.administrator.jingcai;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.jingcai.db.News;
import com.example.administrator.jingcai.util.HttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> newsList;

    public NewsAdapter(List<News> newsList){
        this.newsList = newsList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_news,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.tv_news_title.setText(news.getTitle());
        holder.tv_news_author.setText(news.getAuthorName());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_news_author;
        TextView tv_news_title;
        ImageView iv_news1,iv_news2,iv_news3;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_news_author = itemView.findViewById(R.id.tv_news_author);
            tv_news_title = itemView.findViewById(R.id.tv_news_title);
            iv_news1 = itemView.findViewById(R.id.image_news1);
            iv_news2 = itemView.findViewById(R.id.image_news2);
            iv_news3 = itemView.findViewById(R.id.image_news3);
        }
    }
}
