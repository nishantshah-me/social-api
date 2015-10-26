package com.socialapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.socialapi.Social.SocialBuilder;

/**
 * Created by webwerks on 23/10/15.
 */
public class LogInActivity extends AppCompatActivity implements SocialCallback {

    private Social social;

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
    public void onSocialLoginSuccess(SocialUserProfile socialUserProfile) {
        Log.d("onSocialLoginSuccess", socialUserProfile.toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(LogInActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSocialLoginFailure(String errorMessage) {
       Toast.makeText(LogInActivity.this,"Failed",Toast.LENGTH_SHORT).show();
    }

    public void onFacebookSignIn(View v){
        social=new SocialBuilder()
                .setFacebookApplicationId(getResources().getString(R.string.facebook_app_id))
                .setSocialType(SocialType.FACEBOOK)
                .setCallback(this)
                .build();
        social.login(this);
    }

    public void onSignOut(View v){
        if(Social.logout(this)){
            Toast.makeText(LogInActivity.this,"Logged out successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LogInActivity.this,"Failed",Toast.LENGTH_SHORT).show();
        }
    }


}
