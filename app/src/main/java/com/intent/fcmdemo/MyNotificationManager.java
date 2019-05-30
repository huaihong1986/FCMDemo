package com.intent.fcmdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by Administrator on 5/24/2017.
 */

public class MyNotificationManager {

    private Context mcontext;
    public static final int noid = 255;

    public MyNotificationManager(Context context) {

        mcontext = context;
    }

    public void showNotification(String from, String notification, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mcontext,
                noid,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        Notification.Builder builder = null;

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
        mManager.notify(noid, mnotification);

    }
}
