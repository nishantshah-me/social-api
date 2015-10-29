package com.socialapi.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.socialapi.Config;
import com.socialapi.ServerRequest;
import com.socialapi.Social;
import com.socialapi.SocialType;
import com.socialapi.SocialUserProfile;
import com.socialapi.response.FacebookResponse;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Nishant on 27/10/15.
 */
public class FacebookService extends AbstractSocialService {

    private CallbackManager callbackManager;

    private String facebookApplicationId;

    private FacebookService(){}

    public FacebookService(String facebookApplicationId) {
        this.facebookApplicationId=facebookApplicationId;
    }

    @Override
    public SocialType getType() {
        return SocialType.FACEBOOK;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void serviceLogin(final Activity activity) {
        callbackManager = CallbackManager.Factory.create();
        Config.debug("login");
        FacebookSdk.sdkInitialize(activity);
        FacebookSdk.setApplicationId(facebookApplicationId);
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        // App code
                        ServerRequest.dismissProgressBar();
                        getFacebookData(loginResult.getAccessToken(),activity);
                        Config.debug("onSuccess", loginResult.toString());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        ServerRequest.dismissProgressBar();
                        Config.debug("onCancel");
                        activity.finish();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        ServerRequest.dismissProgressBar();
                        exception.printStackTrace();
                        activity.finish();
                    }
                });
    }



    private void getFacebookData(final AccessToken accessToken, final Activity activity) {

//        ServerRequest.showProgressBar(social.getProgressBar());
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        Config.debug(object.toString());
                        // Application code
                        try {
                            Gson gson = new Gson();
                            final FacebookResponse facebookResponse = gson.fromJson(object.toString(), FacebookResponse.class);
                            facebookResponse.setProfilePicture("https://graph.facebook.com/" + facebookResponse.getId() + "/picture?type=large");
                            activity.finish();
                            Social.getSingleton().getCallback().onSocialLoginSuccess(
                                    new SocialUserProfile(facebookResponse, accessToken), SocialType.FACEBOOK);

                        } catch (Exception e) {
                            e.printStackTrace();
                            activity.finish();
                            Social.getSingleton().getCallback().onSocialLoginFailure("Failed");
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,link,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
