package com.example.administrator.jingcai;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jingcai.db.News;
import com.example.administrator.jingcai.db.NewsTitle;

import java.util.List;

public class NewsTitleAdapter extends RecyclerView.Adapter<NewsTitleAdapter.ViewHolder> {
    private List<NewsTitle> newsList;

    public NewsTitleAdapter(List<NewsTitle> newsList){
        this.newsList = newsList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_news_title,parent,false);
       ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsTitle newsTitle = newsList.get(position);
        holder.btn_news_title.setText(newsTitle.getTitle());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView btn_news_title;
        public ViewHolder(View itemView) {
            super(itemView);
            btn_news_title = itemView.findViewById(R.id.btn_news_title);
        }
    }
}
