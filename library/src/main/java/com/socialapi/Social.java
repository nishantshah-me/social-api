package com.socialapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ProgressBar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.socialapi.response.FacebookResponse;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Nishant on 19/10/15.
 */
public class Social implements Serializable {


    private SocialCallback callback;
    private String facebookApplicationId;
    public static Social social;
    private SocialType socialType;


    private Social(SocialBuilder builder) {
        this.socialType = builder.socialType;
        this.callback = builder.callback;
        this.facebookApplicationId = builder.facebookApplicationId;
    }

    public static Social newInstance() {
        return social;
    }

    public void login(Activity activity) {
        Intent intent = new Intent(activity, VirtualActivity.class);
        activity.startActivity(intent);
    }

    public static boolean logout(Activity activity){
        try {
            FacebookSdk.sdkInitialize(activity);
            LoginManager.getInstance().logOut();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public String getFacebookApplicationId() {
        return facebookApplicationId;
    }

    public SocialCallback getCallback() {
        return callback;
    }


    public static class SocialBuilder {
        private SocialType socialType;
        private SocialCallback callback;
        private String facebookApplicationId;

        public SocialBuilder setFacebookApplicationId(String facebookApplicationId) {
            this.facebookApplicationId = facebookApplicationId;
            return this;
        }

        public SocialBuilder setSocialType(SocialType socialType) {
            this.socialType = socialType;
            return this;
        }

        public SocialBuilder setCallback(SocialCallback callback) {
            this.callback = callback;
            return this;
        }

        public Social build() {
            social = new Social(this);
            return social;
        }
    }

}
