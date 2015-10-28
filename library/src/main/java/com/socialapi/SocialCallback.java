package com.socialapi;

/**
 * Created by Nishant on 19/10/15.
 */
public interface SocialCallback {

    void onSocialLoginSuccess(SocialUserProfile socialUserProfile);
    void onSocialLoginFailure(String errorMessage);
}
