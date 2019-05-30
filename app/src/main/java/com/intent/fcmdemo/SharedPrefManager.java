package com.intent.fcmdemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 5/24/2017.
 */

public class SharedPrefManager {
    private  static final    String Shared = "kamal";
    private static  final String key ="lamak";

    private  static Context mcontext;
    private  static SharedPrefManager sharedPrefManager;

    private SharedPrefManager(Context context){

        mcontext = context;
    }

    static synchronized SharedPrefManager getInstance(Context context){
        if (sharedPrefManager == null)
            sharedPrefManager = new SharedPrefManager(context);
        return sharedPrefManager;
    }

    public boolean storeToken(String token){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,token);
        editor.apply();
        return true;
    }
    public String getToken(){

        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(Shared,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }
}
