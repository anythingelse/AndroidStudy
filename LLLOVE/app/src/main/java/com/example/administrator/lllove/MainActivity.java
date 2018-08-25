package com.example.administrator.lllove;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity{
    MediaPlayer mediaPlayer1,mediaPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_main);
        setContentView(new MyView(this));
        mediaPlayer1 = MediaPlayer.create(this,R.raw.zhiduanqingchang);
        mediaPlayer2 = MediaPlayer.create(this,R.raw.chuanshao);
        mediaPlayer2.setNextMediaPlayer(mediaPlayer1);
        mediaPlayer2.start();
    }
}
