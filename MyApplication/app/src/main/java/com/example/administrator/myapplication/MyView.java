package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MyView extends View {
    private Bitmap bitmap1,bitmap2,bitmap3;
    private Canvas canvas;
    private Paint paint;
    private int heigth,width;
    private int rWidth,rStyle=-1;
    private int t = 0;
    private TextView textView;
    private Button btn1, btn2, btn3;
    private String phonenum = "19923295717";
    private ImageView imageView;
    public MyView(Context context) {
        super(context);
        bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.small_heart);
        bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.small_five);
        bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.sugar);
        paint = new Paint();
        canvas = new Canvas();

    }
    public boolean onTouchEvent(MotionEvent event) {
        int Action = event.getAction();
        if (Action == 0) {
            t++;
//            call(phonenum);
//            Toast.makeText(this, "Call19923295717", Toast.LENGTH_SHORT).show();
            textView.setText("" + t);
            if (t%5==0){
                Random random = new Random();
                DisplayMetrics dm = getResources().getDisplayMetrics();
                heigth = dm.heightPixels;
                width = dm.widthPixels;
                rStyle = random.nextInt(2);
                rWidth = random.nextInt(width);
            }
            if (t == 10) {
                btn1.setVisibility(View.VISIBLE);
            } else if (t == 20) {
                btn2.setVisibility(View.VISIBLE);
            } else if (t == 30) {
                btn3.setVisibility(View.VISIBLE);
            } else if (t == 40) {
//                imageView.setVisibility(View.VISIBLE);
            }else if (t==50){

            }
        }
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        switch (rStyle) {
            case 0:
                canvas.drawBitmap(bitmap1, rWidth, 1, paint);
                break;
            case 1:
                canvas.drawBitmap(bitmap2, rWidth, 1, paint);
                break;
            case 2:
                canvas.drawBitmap(bitmap3, rWidth, 1, paint);
                break;
        }
        super.onDraw(canvas);
    }
}
