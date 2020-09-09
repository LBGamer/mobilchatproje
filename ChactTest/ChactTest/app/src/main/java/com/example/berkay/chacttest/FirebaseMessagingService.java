package com.example.berkay.chacttest;

import android.support.v4.app.NotificationCompat;
import  android.app.NotificationManager;
import com.google.firebase.messaging.RemoteMessage;



public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Friend Request")
                .setContentText("You've received a new Friend Request");

        int mNotificationManager =(int)System.currentTimeMillis();
        NotificationManager mNotifMgr=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifMgr.notify(mNotificationManager,mBuilder.build());
    }
}
