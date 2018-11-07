package com.example.administrator.kankanbook.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.adapter.BookShelfAdapter;
import com.example.administrator.kankanbook.db.BookShelfList;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class BookShelfFragment extends Fragment {

    private RecyclerView shelfRecyclerView;
    private BookShelfAdapter adapter;
    private List<BookShelfList> mBookShelfList = new ArrayList<>();
    private List<BookShelfList> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookshelf,container,false);
        shelfRecyclerView = view.findViewById(R.id.rv_bookshelf);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        shelfRecyclerView.setLayoutManager(manager);
        adapter = new BookShelfAdapter(mBookShelfList,getActivity());
        shelfRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = DataSupport.findAll(BookShelfList.class);
        if (list.size() > 0) {
            mBookShelfList.clear();
            mBookShelfList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

}
