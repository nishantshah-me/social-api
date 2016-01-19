package com.socialapi.model;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;

import com.socialapi.Social;
import com.socialapi.SocialType;
import com.socialapi.SocialUserProfile;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Nishant on 8/1/16.
 */
public class TwitterService extends AbstractSocialService  {

    private String consumerKey;
    private String consumerSecret;
    private TwitterLoginButton loginButton;

    private TwitterService(){}

    public TwitterService(String consumerKey, String consumerSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }

    @Override
    public SocialType getType() {
        return SocialType.TWITTER;
    }

    @Override
    public void initLogin(Activity activity) {
        TwitterAuthConfig authConfig =
                new TwitterAuthConfig(consumerKey,
                        consumerSecret);
        Fabric.with(activity, new TwitterCore(authConfig));
        // Check if already logged in

        loginButton=new TwitterLoginButton(activity);

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                Social.getSingleton().getCallback().onSocialLoginSuccess(
                        new SocialUserProfile(result), SocialType.TWITTER);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Social.getSingleton().getCallback().onSocialLoginFailure("Failed");
            }
        });
        loginButton.performClick();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {

    }
}
