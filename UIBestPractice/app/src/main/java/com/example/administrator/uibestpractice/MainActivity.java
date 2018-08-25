package com.example.administrator.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText et_input;
    private Button btn_send;
    private RecyclerView recyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        et_input = findViewById(R.id.et_input);
        btn_send = findViewById(R.id.btn_send);
        recyclerView = findViewById(R.id.rw_msg);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        recyclerView.setAdapter(adapter);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_input.getText().toString();
                if (!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);//当有新消息时，刷新RecyclerView的显示
                    recyclerView.scrollToPosition(msgList.size()-1);//将RecyclerView定位到最后一行
                    et_input.setText("");//清空输入框
                }
            }
        });
    }
    private void initMsg(){
        Msg msg1 = new Msg("Hello guy!",Msg.TYPE_RECEIVD);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hi!What's wrong?",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("I want make some money!",Msg.TYPE_RECEIVD);
        msgList.add(msg3);
    }
}
