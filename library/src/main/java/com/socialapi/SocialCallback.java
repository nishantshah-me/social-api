package com.socialapi;

/**
 * Created by Nishant on 19/10/15.
 */
public interface SocialCallback {

    void onSocialLoginSuccess(SocialUserProfile socialUserProfile,SocialType socialType);
    void onSocialLoginFailure(String errorMessage);
}
