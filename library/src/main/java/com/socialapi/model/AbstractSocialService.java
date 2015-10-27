package com.socialapi.model;

import android.app.Activity;
import android.content.Intent;

import com.socialapi.SocialCallback;
import com.socialapi.SocialUserProfile;
import com.socialapi.VirtualActivity;

/**
 * Created by webwerks on 27/10/15.
 */
public abstract class AbstractSocialService<T extends Activity & SocialCallback> implements SocialCallback {


    public static String type;
    public static String facebookAppId;
    public static SocialCallback callback;

    private AbstractSocialService() {
    }

    public AbstractSocialService(String facebookApplicationId, String type) {
        this();
        this.setType(type);
        AbstractSocialService.facebookAppId=facebookApplicationId;
    }

    @Override
    public void onSocialLoginFailure(String errorMessage) {
        callback.onSocialLoginFailure(errorMessage);
    }

    @Override
    public void onSocialLoginSuccess(SocialUserProfile socialUserProfile) {
        callback.onSocialLoginSuccess(socialUserProfile);
    }

    public String getFacebookAppId() {
        return facebookAppId;
    }

    public void login(Activity activity,SocialCallback callback) {
        AbstractSocialService.callback=callback;
        Intent intent = new Intent(activity, VirtualActivity.class);
        activity.startActivity(intent);
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        AbstractSocialService.type = type;
    }


}
