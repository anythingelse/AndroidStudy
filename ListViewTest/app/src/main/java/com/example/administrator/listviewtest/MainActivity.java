package com.example.administrator.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private String[] data = {"qqq","www","eee","rrr","ttt",
//    "yyy","uuu","iii","ooo","ppp","aaa","sss","ddd","fff"};
    private List<Fruit> fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
//                android.R.layout.simple_list_item_1,data);
//        ListView listView = findViewById(R.id.lv);
//        listView.setAdapter(arrayAdapter);

        initFruits();
        FruitAdapter fruitAdapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
        ListView listView = findViewById(R.id.lv);
        listView.setAdapter(fruitAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getname(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initFruits(){
        for (int i = 0;i < 3;i++){
            Fruit qqq = new Fruit("qqq",R.drawable.ic_launcher_background);
            fruitList.add(qqq);
            Fruit www = new Fruit("www",R.drawable.ic_launcher_background);
            fruitList.add(www);
            Fruit eee = new Fruit("eee",R.drawable.ic_launcher_background);
            fruitList.add(eee);
            Fruit rrr = new Fruit("rrr",R.drawable.ic_launcher_background);
            fruitList.add(rrr);
            Fruit ttt = new Fruit("ttt",R.drawable.ic_launcher_background);
            fruitList.add(ttt);
            Fruit yyy = new Fruit("yyy",R.drawable.ic_launcher_background);
            fruitList.add(yyy);
            Fruit uuu = new Fruit("uuu",R.drawable.ic_launcher_background);
            fruitList.add(uuu);
            Fruit iii = new Fruit("iii",R.drawable.ic_launcher_background);
            fruitList.add(iii);
            Fruit ooo = new Fruit("ooo",R.drawable.ic_launcher_background);
            fruitList.add(ooo);
            Fruit ppp = new Fruit("ppp",R.drawable.ic_launcher_background);
            fruitList.add(ppp);
        }
    }
}
