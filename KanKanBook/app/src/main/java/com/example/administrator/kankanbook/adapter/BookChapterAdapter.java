package com.example.administrator.kankanbook.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.db.ChapterList;

import java.util.List;
import java.util.zip.Inflater;

public class BookChapterAdapter extends RecyclerView.Adapter<BookChapterAdapter.ViewHolder>{

    private List<ChapterList> chapterLists;
    public BookChapterAdapter(List<ChapterList> lists){
        chapterLists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chapter_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChapterList chapterList = chapterLists.get(position);
        holder.text_chapter.setText(chapterList.getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text_chapter;
        public ViewHolder(View itemView) {
            super(itemView);
            text_chapter = itemView.findViewById(R.id.text_chapter);
        }
    }
}
