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
import com.google.firebase.messaging.FirebaseMessaging;

import static com.intent.fcmdemo.MyFirebaseMessagingService.ELITOR_CLOCK;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String TOPIC ="test";
    private TextView textview;
    private BroadcastReceiver broadcastReceiver;
    public static boolean isForeground = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "data=onCreate");
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TAG", "Refreshed token:Now " + refreshedToken);
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);
        textview = (TextView)findViewById(R.id.text);
        if(getIntent()!=null && getIntent().getStringExtra("type")!=null){
            String mNotification = getIntent().getStringExtra("type");
            Log.d(TAG, "data=" + mNotification);
        }
//        broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                textview.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
//            }
//        };
//
//        if (SharedPrefManager.getInstance(this).getToken() != null){
//            textview.setText(SharedPrefManager.getInstance(MainActivity.this).getToken());
//        }
//
//        registerReceiver(broadcastReceiver,new IntentFilter(MyFirebaseInstanceIdService.br));

        //实例化IntentFilter对象
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(ELITOR_CLOCK);
//        broadcastReceiver = new MyReceiver();
//        //注册广播接收
//        registerReceiver(broadcastReceiver,filter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "data=onNewIntent");
        if(intent!=null && intent.getStringExtra("type")!=null){
            String mNotification =intent.getStringExtra("type");
            Log.d(TAG, "data=" + mNotification);
        }
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isForeground = false;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}
