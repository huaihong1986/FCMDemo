package com.intent.fcmdemo;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Administrator on 5/24/2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

        public static final String br = "brc";
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TAG", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

        getApplicationContext().sendBroadcast(new Intent(br));
        storeToken(refreshedToken);
    }

    private void storeToken(String refreshedToken) {
        SharedPrefManager.getInstance(getApplicationContext()).storeToken(refreshedToken);

    }


}
