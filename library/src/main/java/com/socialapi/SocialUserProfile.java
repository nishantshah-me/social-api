package com.socialapi;

import com.facebook.AccessToken;
import com.socialapi.response.FacebookResponse;

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
    private String profilePicture;
    private AccessToken accessToken;

    public SocialUserProfile(FacebookResponse facebookResponse,AccessToken accessToken) {
        this.firstName = facebookResponse.getFirst_name();
        this.lastName = facebookResponse.getLast_name();
        this.email = facebookResponse.getEmail();
        this.socialID = facebookResponse.getId();
        this.fullName = facebookResponse.getFirst_name()+" "+facebookResponse.getLast_name();
        this.accessToken=accessToken;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
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



    @Override
    public String toString() {
        return "SocialUserProfile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", socialID='" + socialID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", token='" + token + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
