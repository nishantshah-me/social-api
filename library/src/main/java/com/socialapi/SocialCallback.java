package com.socialapi;

import java.io.Serializable;

/**
 * Created by webwerks on 19/10/15.
 */
public interface SocialCallback extends Serializable {

    void onSocialLoginSuccess(SocialUserProfile socialUserProfile);
    void onSocialLoginFailure(String errorMessage);
}
