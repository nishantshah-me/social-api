package com.socialapi.model;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.socialapi.SocialType;

/**
 * Created by webwerks on 29/10/15.
 */
public class GoogleService extends AbstractSocialService {


    @Override
    public SocialType getType() {
        return SocialType.GOOGLE;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void serviceLogin(Activity activity) {
        Toast.makeText(activity,"G+",Toast.LENGTH_LONG).show();
    }


}
