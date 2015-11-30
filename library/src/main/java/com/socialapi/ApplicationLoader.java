package com.socialapi;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by Nishant on 05-Jun-15.
 */
public class ApplicationLoader extends Application {


    private static ApplicationLoader mInstance;
    private RequestQueue mRequestQueue;
    private String TAG=ApplicationLoader.class.getSimpleName();
    private static String applicationType;
    private static String applicationID;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        Social.initServices(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }


    public static ApplicationLoader getInstance() {
        return mInstance;
    }


    public SharedPreferences getSharedPreferenceManager(){
        SharedPreferences preferencesReader = getSharedPreferences("CACHE", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = preferencesReader.edit();
        return preferencesReader;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
      /*  req.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
        getRequestQueue() .add(req);
    }

    public <T> void addToRequestQueueInBackground(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public String getApplicationType() {
        //home , personal ,education
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        //home , personal ,education
        ApplicationLoader.applicationType = applicationType;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        ApplicationLoader.applicationID = applicationID;
    }


}
