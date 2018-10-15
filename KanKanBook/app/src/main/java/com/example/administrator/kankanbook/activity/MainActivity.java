package com.example.administrator.kankanbook.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.kankanbook.R;
import com.example.administrator.kankanbook.db.RankingList;
import com.example.administrator.kankanbook.fragment.BookShelfFragment;
import com.example.administrator.kankanbook.fragment.BookStoreFragment;
import com.example.administrator.kankanbook.fragment.MeFragment;

import org.litepal.crud.DataSupport;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    private ImageView title_back;
    private TextView tv_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataSupport.deleteAll(RankingList.class);
        initView();
    }

    public void initView(){
        getFragmentManager().beginTransaction().replace(R.id.frame_layout,new BookShelfFragment()).commit();
        title_back = findViewById(R.id.title_back);
        tv_title = findViewById(R.id.tv_title);
        title_back.setVisibility(View.GONE);
        tv_title.setText("书架");
        bottomNavigationView = findViewById(R.id.navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.tab_menu_bookshelf);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_menu_bookstore:
                        getFragmentManager().beginTransaction().replace(R.id.frame_layout,new BookStoreFragment()).commit();
                        tv_title.setText("书城");
                        break;
                    case R.id.tab_menu_bookshelf:
                        getFragmentManager().beginTransaction().replace(R.id.frame_layout,new BookShelfFragment()).commit();
                        tv_title.setText("书架");
                        break;
                    case R.id.tab_menu_me:
                        getFragmentManager().beginTransaction().replace(R.id.frame_layout,new MeFragment()).commit();
                        tv_title.setText("我的");
                        break;
                }

                return true;
            }
        });
    }
}
