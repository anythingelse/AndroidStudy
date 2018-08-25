package com.example.administrator.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);             //横向滚动
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);          //瀑布式滚动
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);
    }
    private void initFruits(){
        for (int i = 0;i < 3;i++){
            Fruit qqq = new Fruit(getRandomLengthName("qqq"),R.drawable.ic_launcher_background);
            fruitList.add(qqq);
            Fruit www = new Fruit(getRandomLengthName("www"),R.drawable.ic_launcher_background);
            fruitList.add(www);
            Fruit eee = new Fruit(getRandomLengthName("eee"),R.drawable.ic_launcher_background);
            fruitList.add(eee);
            Fruit rrr = new Fruit(getRandomLengthName("rrr"),R.drawable.ic_launcher_background);
            fruitList.add(rrr);
            Fruit ttt = new Fruit(getRandomLengthName("ttt"),R.drawable.ic_launcher_background);
            fruitList.add(ttt);
            Fruit yyy = new Fruit(getRandomLengthName("yyy"),R.drawable.ic_launcher_background);
            fruitList.add(yyy);
            Fruit uuu = new Fruit(getRandomLengthName("uuu"),R.drawable.ic_launcher_background);
            fruitList.add(uuu);
            Fruit iii = new Fruit(getRandomLengthName("iii"),R.drawable.ic_launcher_background);
            fruitList.add(iii);
            Fruit ooo = new Fruit(getRandomLengthName("ooo"),R.drawable.ic_launcher_background);
            fruitList.add(ooo);
            Fruit ppp = new Fruit(getRandomLengthName("ppp"),R.drawable.ic_launcher_background);
            fruitList.add(ppp);
        }
    }

    private String getRandomLengthName(String string){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < length;i++){
            builder.append(string);
        }
        return builder.toString();
    }
}
