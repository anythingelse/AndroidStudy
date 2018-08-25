package com.example.administrator.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
//        //向下一个活动传递数据
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Log.d("SecondActivity",data);


        Button button = findViewById(R.id.btn_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //返回数据给上一个活动
//                Intent intent = new Intent();
//                intent.putExtra("data_return","Hello FirstActivity");
//                setResult(RESULT_OK,intent);
//                finish();
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
    //返回数据给上一个活动

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("data_return","Hello FirstActivity");
        setResult(RESULT_OK,intent);
        finish();
    }
    public static void actionStart(Context context,String string1,String string2){
        Intent intent = new Intent(context,SecondActivity.class);
        intent.putExtra("param1",string1);
        intent.putExtra("param2",string2);
        context.startActivity(intent);
    }
}
