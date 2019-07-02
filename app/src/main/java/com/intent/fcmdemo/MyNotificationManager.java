package com.intent.fcmdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.intent.fcmdemo.MyFirebaseMessagingService.ELITOR_CLOCK;

public class MyNotificationManager {

    private final static  String TAG = "MyNotificationManager";
    private Context mcontext;

    public MyNotificationManager(Context context) {

        mcontext = context;
    }

    public void showNotification(String from, String notification, Intent intent) {
        Notification.Builder builder = null;
        int id = (int) (System.currentTimeMillis() / 1000);
        //CLICK ON NOTIFICATION
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(mcontext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mcontext,
                id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
//        Intent mediaPlayerReceiver = new Intent(ELITOR_CLOCK);
//        mediaPlayerReceiver.putExtra("notification", "1");
//        mediaPlayerReceiver.setClass(mcontext, MyReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(mcontext,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationManager mManager = (NotificationManager) mcontext.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder = new Notification.Builder(mcontext).setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(from)
                    .setContentText(notification);
        } else {
/**
 * Oreo不用Priority了，用importance
 * IMPORTANCE_NONE 关闭通知
 * IMPORTANCE_MIN 开启通知，不会弹出，但没有提示音，状态栏中无显示
 * IMPORTANCE_LOW 开启通知，不会弹出，不发出提示音，状态栏中显示
 * IMPORTANCE_DEFAULT 开启通知，不会弹出，发出提示音，状态栏中显示
 * IMPORTANCE_HIGH 开启通知，会弹出，发出提示音，状态栏中显示
 */
            NotificationChannel notificationChannel = new
                    NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_HIGH); //如果这里用IMPORTANCE_NOENE就需要在系统的设置里面开启渠道， //通知才能正常弹出
            mManager.createNotificationChannel(notificationChannel);
            builder = new Notification.Builder(mcontext, "channel_id").setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(from)
                    .setContentText(notification);
        }

        Notification mnotification = builder.build();
        mManager.notify(id, mnotification);
        Log.e(TAG,"data=showNotification"+from);
    }
}
