package com.intent.fcmdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Administrator on 5/24/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String ELITOR_CLOCK = "ELITOR_CLOCK";
    private static final String TAG = "message";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        String type = "1";
        String title = "Color Peace";
        String body = "New images available for coloring!";
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message data Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.


        nofiyUser(title, body, type);
    }

// Check if message contains a notification payload.


// Also if you intend on generating your own notifications as a result of a received FCM
// message, here is where that should be initiated. See sendNotification method below.


    public void nofiyUser(String title, String Notification, String type) {
        Log.e(TAG,"data=nofiyUser"+type);
        try {
            MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext());
//      Intent notificationIntent = new Intent(getApplicationContext(), com.app.push.MyReceiver.class);
//      notificationIntent.setAction(ELITOR_CLOCK);
            Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
            notificationIntent.putExtra("type", type);
            myNotificationManager.showNotification(title, Notification, notificationIntent);
        } catch (Throwable throwable) {
Log.e(TAG,"data="+throwable.toString());
        }
    }
}
