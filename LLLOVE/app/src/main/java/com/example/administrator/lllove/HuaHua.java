package com.example.administrator.lllove;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class HuaHua {
    public int type;
    public int width,hight;
    public static final int TYPE_HEART=1;
    public static final int TYPE_FIVE=2;
    public static final int TYPE_SUGAR=3;
    public Bitmap bmp;
    public int x,y;
    public int speed;
    public boolean isDead;
    public HuaHua(Bitmap bmp,int type,int x, int y){
        this.bmp = bmp;
        this.x = x;
        this.y = y;
        this.type = type;
        width = bmp.getWidth();
        hight = bmp.getHeight();
        switch (type){
            case 1:speed=5;break;
            case 2:speed=10;break;
            case 3:speed=15;break;
        }
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.drawBitmap(bmp, x , y, paint);
        canvas.restore();
    }
    public void logic() {
        if (isDead==false){
            y+=speed;
            if (y>MyView.heigth+10){
                isDead=true;
            }
        }
    }
    public boolean isCollsionWith(int xP,int yP){
        if (x >= xP ) {
            return false;
        } else if (x +width<= xP ) {
            return false;
        } else if (y >= yP ) {
            return false;
        } else if (y +hight<= yP ) {
            return false;
        }
        //发生碰撞，让其死亡
        isDead = true;
        return true;
    }
}
