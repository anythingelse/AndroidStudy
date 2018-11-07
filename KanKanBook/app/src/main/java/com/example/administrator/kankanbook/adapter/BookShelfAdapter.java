package com.example.administrator.kankanbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.activity.BookContentActivity;
import com.example.administrator.kankanbook.db.BookShelfList;
import com.example.administrator.kankanbook.db.ChapterList;

import org.litepal.crud.DataSupport;

import java.util.List;

public class BookShelfAdapter extends RecyclerView.Adapter<BookShelfAdapter.ViewHolder>{

    private List<BookShelfList> mBookShelfLists;
    private Context mContext;

    public BookShelfAdapter(List<BookShelfList> lists,Context context){
        mBookShelfLists = lists;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookshelf_list_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.shelfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                List<ChapterList> chapterLists = DataSupport.where("bookId = ?",
                        mBookShelfLists.get(position).getBookId()).find(ChapterList.class);
                ChapterList chapterList = chapterLists.get(mBookShelfLists.get(position).getOrder()-1);
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
        BookShelfList bookShelfList = mBookShelfLists.get(position);
        holder.shelf_bookTitle.setText(bookShelfList.getBookTitle());
        holder.shelf_bookAuthor.setText(bookShelfList.getBookAuthor());
        Glide.with(mContext).load(bookShelfList.getBookPicUrl()).into(holder.shelf_img);
    }

    @Override
    public int getItemCount() {
        return mBookShelfLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView shelf_bookTitle,shelf_bookAuthor;
        ImageView shelf_img;
        View shelfView;
        public ViewHolder(View itemView) {
            super(itemView);
            shelfView = itemView;
            shelf_bookAuthor = itemView.findViewById(R.id.shelf_author);
            shelf_bookTitle = itemView.findViewById(R.id.shelf_bookTitle);
            shelf_img = itemView.findViewById(R.id.shelf_img);
        }
    }
}
