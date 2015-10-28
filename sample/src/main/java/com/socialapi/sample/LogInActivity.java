package com.socialapi.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.socialapi.Config;
import com.socialapi.Social;
import com.socialapi.SocialCallback;
import com.socialapi.SocialUserProfile;
import com.socialapi.model.FacebookService;

/**
 * Created by Nishant on 23/10/15.
 */
public class LogInActivity extends AppCompatActivity implements SocialCallback {

    private Social social;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        social=new Social(this,this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Config.debug("onResume");
    }

    @Override
    public void onSocialLoginSuccess(SocialUserProfile socialUserProfile) {
        Config.debug("onSocialLoginSuccess", socialUserProfile.toString());
        Toast.makeText(LogInActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSocialLoginFailure(String errorMessage) {
       Toast.makeText(LogInActivity.this,"Failed",Toast.LENGTH_SHORT).show();
    }

    public void onFacebookSignIn(View v){
        social.login(new FacebookService(getResources().getString(R.string.facebook_app_id)));
    }

    public void onSignOut(View v){
        if(Social.logout(this)){
            Toast.makeText(LogInActivity.this,"Logged out successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LogInActivity.this,"Failed",Toast.LENGTH_SHORT).show();
        }
    }


}
