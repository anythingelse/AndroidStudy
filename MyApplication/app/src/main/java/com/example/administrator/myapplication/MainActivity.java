package com.example.administrator.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity{

//    private int t = 0,width;
//    private TextView textView;
//    private Button btn1, btn2, btn3;
//    private String phonenum = "19923295717";
//    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        textView = findViewById(R.id.tv1);
//        btn1 = findViewById(R.id.button1);
//        btn2 = findViewById(R.id.button2);
//        btn3 = findViewById(R.id.button3);
//        imageView = findViewById(R.id.iv);
        setContentView(new MyView(this));

    }

//    public boolean onTouchEvent(MotionEvent event) {
//        int Action = event.getAction();
//        if (Action == 0) {
//            t++;
////            call(phonenum);
////            Toast.makeText(this, "Call19923295717", Toast.LENGTH_SHORT).show();
//            textView.setText("" + t);
//            if (t%5==0){
//
//            }
//            if (t == 10) {
//                btn1.setVisibility(View.VISIBLE);
//            } else if (t == 20) {
//                btn2.setVisibility(View.VISIBLE);
//            } else if (t == 30) {
//                btn3.setVisibility(View.VISIBLE);
//            } else if (t == 40) {
////                imageView.setVisibility(View.VISIBLE);
//            }else if (t==50){
//
//            }
//        }
//        return true;
//    }
//
//    private void call(String phone) {
//        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }
////    private void Ondraw(Canvas canvas,int i,int width){
////        Paint paint = new Paint();
////        switch (i){
////            case 0:canvas.drawBitmap(bitmap1,width,1,paint);break;
////            case 1:canvas.drawBitmap(bitmap2,width,1,paint);break;
////            case 2:canvas.drawBitmap(bitmap3,width,1,paint);break;
////        }
////    }
}
