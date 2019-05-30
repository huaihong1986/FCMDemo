package com.intent.fcmdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;


public class MainActivity extends AppCompatActivity {

    private TextView textview;
    private BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TAG", "Refreshed token:Now " + refreshedToken);
        textview = (TextView)findViewById(R.id.text);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                textview.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
            }
        };

        if (SharedPrefManager.getInstance(this).getToken() != null){
            textview.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
        }

        registerReceiver(broadcastReceiver,new IntentFilter(MyFirebaseInstanceIdService.br));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}
