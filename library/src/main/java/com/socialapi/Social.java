package com.socialapi;

import android.app.Activity;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.socialapi.model.AbstractSocialService;

/**
 * Created by Nishant on 19/10/15.
 */
public class Social<T extends Activity & SocialCallback> {


//    private T callback;
    private Activity activity;
    private SocialCallback callback;



    public Social(Activity activity,SocialCallback callback) {
       this.activity=activity;
       this.callback=callback;

    }

    public void login(AbstractSocialService abstractSocialService) {
       abstractSocialService.login(activity,callback);
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

    public SocialCallback getCallback() {
        return callback;
    }




}
