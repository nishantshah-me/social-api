package com.socialapi;

import android.util.Log;

/**
 * Created by webwerks on 23/10/15.
 */
public class Config {

    public static void debug(String tag,String message){
        Log.d("debug "+tag,message);
    }
    public static void debug(String message){
        Log.d("debug ",message);
    }
}
