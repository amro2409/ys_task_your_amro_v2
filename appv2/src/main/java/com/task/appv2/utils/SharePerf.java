package com.task.appv2.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;




public class SharePerf {

    //define
    private final Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static SharePerf mInstance;


    //constructor
    private SharePerf(Context mContext) {
        this.mContext = mContext;
    }

    //get instance
    public static SharePerf getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharePerf(context);
        }
        return mInstance;
    }

    public void addLoggedIn(boolean isLogged) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("UserFile", Context.MODE_PRIVATE).edit();

        editor.putBoolean("isLogged", isLogged);

        editor.apply();
    }


    public boolean isLoggedIn() {
        final SharedPreferences sharedPreferences = mContext.getSharedPreferences("UserFile", Context.MODE_PRIVATE);
        return sharedPreferences.contains("isLogged") && !sharedPreferences.getBoolean("isLogged", false);
    }

    //-----------------------------------------------
    public boolean LogoutUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("UserFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
        return true;
    }

}
