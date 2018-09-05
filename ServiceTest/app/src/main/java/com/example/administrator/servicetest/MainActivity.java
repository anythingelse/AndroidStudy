package com.example.administrator.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_start,btn_stop;
    private Button btn_bind,btn_unbind;
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder)service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_bind = findViewById(R.id.btn_bind);
        btn_unbind = findViewById(R.id.btn_unbind);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_bind.setOnClickListener(this);
        btn_unbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                Intent intent = new Intent(this,MyService.class);
                startService(intent);
                break;
            case R.id.btn_stop:
                Intent intent1 = new Intent(this,MyService.class);
                stopService(intent1);
                break;
            case R.id.btn_bind:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(connection);
                break;
                default:break;
        }
    }
}
