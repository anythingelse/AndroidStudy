package com.example.administrator.lllove;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;
import java.util.Vector;

public class MyView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder sfh;
    private Paint paint;
    private Thread th;
    private boolean flag;
    private Bitmap bitmap1,bitmap2,bitmap3,bitmap_heart;
    private Canvas canvas;
    public static int heigth,width;
    private int rWidth,rStyle;
    public  int t = 0,count=0,size = 15;
    private String string="哈婆娘";
    private Vector<HuaHua> vcHua;
    private int xP,yP;
    public MyView(Context context) {
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(25);
        paint.setAntiAlias(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        //设置背景常亮
        this.setKeepScreenOn(true);
    }
    private void init(){
        bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.small_heart);
        bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.small_five);
        bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.sugar);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.heart);
        bitmap_heart = Bitmap.createScaledBitmap(bmp,width,heigth/2,true);
        vcHua = new Vector<HuaHua>();
    }
    public boolean onTouchEvent(MotionEvent event) {
        int Action = event.getAction();
        if (Action == 0) {
            xP = (int) event.getX();
            yP = (int) event.getY();
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        width = this.getWidth();
        heigth = this.getHeight();
        init();
        flag = true;
        //实例线程
        th = new Thread(this);
        //启动线程
        th.start();
    }

    private void myDraw(){
        canvas = sfh.lockCanvas();
        canvas.drawColor(Color.WHITE);
        canvas.drawText(string,width/2-size,heigth/2-50,paint);
        if (t==520){
            canvas.drawBitmap(bitmap_heart,0,heigth/4,paint);
            canvas.drawText("我爱你，你爱我吗",width/4-size,heigth/2,paint);
        }
        if (vcHua!=null) {
            for (int i = 0; i < vcHua.size(); i++) {
                vcHua.elementAt(i).draw(canvas, paint);
            }
        }
        sfh.unlockCanvasAndPost(canvas);
    }
    private void logic(){
        count++;

        if (vcHua!=null) {
            for (int i = 0; i < vcHua.size(); i++) {
                if (vcHua.elementAt(i).isDead){
                    vcHua.removeElementAt(i);
                }else
                 vcHua.elementAt(i).logic();
            }
        }
        if (count%10==0){
            t++;
            if (t%5==0){
                size+=1;
            }
            if (t==520){
                paint.setColor(Color.RED);
            }
            string=""+t;
            paint.setTextSize(size);
            Random random = new Random();
            rStyle = random.nextInt(3);
            rWidth = random.nextInt(width);
            switch (rStyle){
                case 0:{
                    vcHua.addElement(new HuaHua(bitmap1,1,rWidth,0));
                }break;
                case 1:{
                    vcHua.addElement(new HuaHua(bitmap2,2,rWidth,0));
                }break;
                case 2:{
                    vcHua.addElement(new HuaHua(bitmap3,3,rWidth,0));
                }break;
            }
        }
        for (int i = 0; i < vcHua.size(); i++) {
            vcHua.elementAt(i).isCollsionWith(xP,yP);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            if (t==521){
                flag=false;
            }
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
