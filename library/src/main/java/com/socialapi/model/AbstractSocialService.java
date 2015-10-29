package com.socialapi.model;

import android.app.Activity;
import android.content.Intent;

import com.socialapi.SocialType;

import java.io.Serializable;

/**
 * Created by Nishant on 27/10/15.
 */
public abstract class AbstractSocialService implements Serializable {

    public abstract SocialType getType();

    public abstract void serviceLogin(Activity activity);

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);



}
