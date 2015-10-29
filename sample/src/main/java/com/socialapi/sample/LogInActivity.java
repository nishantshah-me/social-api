package com.socialapi.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.socialapi.Config;
import com.socialapi.Social;
import com.socialapi.SocialCallback;
import com.socialapi.SocialType;
import com.socialapi.SocialUserProfile;
import com.socialapi.model.FacebookService;
import com.socialapi.model.GoogleService;

/**
 * Created by Nishant on 23/10/15.
 */
public class LogInActivity extends AppCompatActivity implements SocialCallback {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Config.debug("onResume");
    }

    @Override
    public void onSocialLoginSuccess(SocialUserProfile socialUserProfile,SocialType socialType) {
        Config.debug("onSocialLoginSuccess", socialUserProfile.toString());
        Toast.makeText(LogInActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSocialLoginFailure(String errorMessage) {
       Toast.makeText(LogInActivity.this,"Failed",Toast.LENGTH_SHORT).show();
    }

    public void onFacebookSignIn(View v){
        Social.with(this).login(new FacebookService(getResources().getString(R.string.facebook_app_id)));
    }

    public void onGoogleSignIn(View v){
        Social.with(this).login(new GoogleService());

    }
    public void onSignOut(View v){
        if(Social.logout(this)){
            Toast.makeText(LogInActivity.this,"Logged out successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LogInActivity.this,"Failed",Toast.LENGTH_SHORT).show();
        }
    }


}
