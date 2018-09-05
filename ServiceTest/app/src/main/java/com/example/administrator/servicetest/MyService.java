package com.example.administrator.servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.provider.DocumentsContract;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();
    private static final String NOTIFICATION_CHANNEL_ID = "test.default";
    private static final String NOTIFICATION_CHANNEL_NAME = "test.default";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","onCreate executed");
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26){
            NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(mChannel);
        }
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("this is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round))
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","onDestroy executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyService","onBind");
        return mBinder;
    }

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService","startDownload");
        }
        public int getProgress(){
            Log.d("MyService","getProgress");
            return 0;
        }
    }
}
