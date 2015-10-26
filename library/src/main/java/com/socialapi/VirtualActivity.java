package com.socialapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
import com.socialapi.response.FacebookResponse;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by webwerks on 23/10/15.
 */
public class VirtualActivity extends Activity {

    private CallbackManager callbackManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        facebookLogin();
    }

    public void facebookLogin(){

        Config.debug("login");
        FacebookSdk.sdkInitialize(this);
        FacebookSdk.setApplicationId(Social.newInstance().getFacebookApplicationId());
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        // App code
                        ServerRequest.dismissProgressBar();
                        getFacebookData(loginResult.getAccessToken());
                        Config.debug("onSuccess", loginResult.toString());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        ServerRequest.dismissProgressBar();
                        Config.debug("onCancel");
                        finish();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        ServerRequest.dismissProgressBar();
                        exception.printStackTrace();
                        finish();
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void getFacebookData(final AccessToken accessToken) {

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
                            VirtualActivity.this.finish();
                            Social.newInstance().getCallback().onSocialLoginSuccess(
                                    new SocialUserProfile(facebookResponse, accessToken));

                        } catch (Exception e) {
                            e.printStackTrace();
                            VirtualActivity.this.finish();
                            Social.newInstance().getCallback().onSocialLoginFailure("Failed");
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,link,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
