package com.example.administrator.kankanbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.activity.BookContentActivity;
import com.example.administrator.kankanbook.db.ChapterList;

import java.util.List;
import java.util.zip.Inflater;

public class BookChapterAdapter extends RecyclerView.Adapter<BookChapterAdapter.ViewHolder>{

    private List<ChapterList> chapterLists;
    private Context mContext;
    public BookChapterAdapter(List<ChapterList> lists,Context context){
        chapterLists = lists;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chapter_list_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.chapterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                ChapterList chapterList = chapterLists.get(position);
                Intent intent = new Intent(mContext,BookContentActivity.class);
                intent.putExtra("title",chapterList.getTitle());
                intent.putExtra("link",chapterList.getLink());
                intent.putExtra("order",chapterList.getOrder());
                intent.putExtra("bookId",chapterList.getBookId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChapterList chapterList = chapterLists.get(position);
        holder.text_chapter.setText(chapterList.getTitle());
    }

    @Override
    public int getItemCount() {
        return chapterLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_chapter;
        View chapterView;
        public ViewHolder(View itemView) {
            super(itemView);
            chapterView = itemView;
            text_chapter = itemView.findViewById(R.id.text_chapter);
        }
    }
}
