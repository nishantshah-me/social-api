package com.socialapi;

import com.facebook.AccessToken;
import com.socialapi.response.FacebookResponse;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Nishant on 19/10/15.
 */
public class SocialUserProfile{

    private String firstName;
    private String lastName;
    private String email;
    private String socialID;
    private String fullName;
    private String token;
    private String secret;
    private String profilePicture;
    private AccessToken facebookAccessToken;

    public SocialUserProfile(FacebookResponse facebookResponse,AccessToken facebookAccessToken) {
        this.firstName = facebookResponse.getFirst_name();
        this.lastName = facebookResponse.getLast_name();
        this.email = facebookResponse.getEmail();
        this.socialID = facebookResponse.getId();
        this.fullName = facebookResponse.getFirst_name()+" "+facebookResponse.getLast_name();
        this.facebookAccessToken = facebookAccessToken;
    }

    public SocialUserProfile() {
    }

    public SocialUserProfile(Result<TwitterSession> result) {
       Config.debug(result.toString());
        TwitterAuthToken authToken = result.data.getAuthToken();
        this.token = authToken.token;
        this.secret = authToken.secret;
    }

    public AccessToken getFacebookAccessToken() {
        return facebookAccessToken;
    }

    public void setFacebookAccessToken(AccessToken facebookAccessToken) {
        this.facebookAccessToken = facebookAccessToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialID() {
        return socialID;
    }

    public void setSocialID(String socialID) {
        this.socialID = socialID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "SocialUserProfile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", socialID='" + socialID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", token='" + token + '\'' +
                ", secret='" + secret + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", facebookAccessToken=" + facebookAccessToken +
                '}';
    }
}
