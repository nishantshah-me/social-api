package com.socialapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.socialapi.model.AbstractSocialService;
import com.socialapi.model.GoogleService;

/**
 * Created by Nishant on 19/10/15.
 */
public class Social {


    private final Activity activity;
    private final SocialCallback callback;
    static volatile Social singleton = null;


    Social(Activity activity, SocialCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    public static void initServices(Context context){
        //init Google Service
        GoogleService.getGoogleAPIClient(context).connect();
    }

    public static Social getSingleton() {
        return singleton;
    }

    public static <T extends Activity & SocialCallback> Social with(T object) {
        if (singleton == null) {
            synchronized (Social.class) {
                if (singleton == null) {
                    singleton = new Builder(object).build();
                }
            }
        }
        return singleton;
    }

    public SocialCallback getCallback() {
        return callback;
    }

    public Activity getActivity() {
        return activity;
    }

    public void login(AbstractSocialService abstractSocialService) {
        Intent intent = new Intent(Social.getSingleton().getActivity(), VirtualActivity.class);
        intent.putExtra("service_object", abstractSocialService);
        Social.getSingleton().getActivity().startActivity(intent);
    }

    public static boolean logout(Activity activity) {
        try {
            facebookLogout(activity);
            googleLogout(activity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private static void googleLogout(Activity activity) {
        GoogleService.signOut(activity);
    }

    private static void facebookLogout(Activity activity) {
        FacebookSdk.sdkInitialize(activity);
        LoginManager.getInstance().logOut();
    }

    static class Builder {

        private Activity activity;
        private SocialCallback callback;

        public <T extends Activity & SocialCallback> Builder(T object) {
            this.activity = object;
            this.callback = object;
        }

        public Social build() {
            return new Social(activity, callback);
        }
    }

}
