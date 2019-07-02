package com.intent.fcmdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.intent.fcmdemo.MyFirebaseMessagingService.ELITOR_CLOCK;


/**
 * author : Aihong
 * e-mail : huaihong1986@sina.com
 * date   : 2019/7/1/16:23
 * desc   :
 * version: 1.0
 */
public class MyReceiver extends BroadcastReceiver {
  public static final String TAG = "MyReceiver";
  private String mAction;

  //该方法用于实现接收到广播的具体处理，其中参数intent：为接受到的intent
  @Override
  public void onReceive(Context context, Intent intent) {
    //获取意图的动作

    //获取意图的动作
    try {
      mAction = intent.getAction();
      String mNotification = intent.getStringExtra("type");
      if (ELITOR_CLOCK.equals(mAction)) {//点击通知回到当前Activity，读取其中信息
        if(!MainActivity.isForeground) {
          Intent intentmain = new Intent(context, MainActivity.class);
          intentmain.putExtra("type", mNotification);
          intentmain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          context.startActivity(intent);
        }else {
          Log.d(TAG, "data=" + mNotification);
        }
      }
    }catch (Throwable throwable){

    }
  }
}

