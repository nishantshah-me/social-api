package com.socialapi.model;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.socialapi.Config;
import com.socialapi.SocialType;

/**
 * Created by webwerks on 29/10/15.
 */
public class GoogleService extends AbstractSocialService implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<People.LoadPeopleResult> {


    private static final int SIGN_IN_REQUEST_GPLUS = 10;
    private GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN = 0;
    private boolean mIntentInProgress;
    private ConnectionResult mConnectionResult;
    private Activity activity;
    private boolean mSignInClicked;


    @Override
    public SocialType getType() {
        return SocialType.GOOGLE;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK) {
            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnected()) {
                mGoogleApiClient.reconnect();
            }
        }
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
    }

    @Override
    public void initLogin(Activity activity) {
        this.activity = activity;
        if (mGoogleApiClient == null) {
            mGoogleApiClient = buildGoogleAPIClient();
            if (!mGoogleApiClient.isConnecting()) {
                mSignInClicked = true;
                mGoogleApiClient.connect();
            }
        }
    }

    private GoogleApiClient buildGoogleAPIClient() {
        return new GoogleApiClient.Builder(activity).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }

    public void reconnectToGplus(int resultcode) {
        if (resultcode != Activity.RESULT_OK) {
            mSignInClicked = false;
        }
        mIntentInProgress = false;
        if (!mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        mSignInClicked = false;
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            Config.debug(currentPerson.getDisplayName());
            Config.debug(Plus.AccountApi.getAccountName(mGoogleApiClient));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), activity, 0).show();
            return;
        }
        if (!mIntentInProgress) {
            mConnectionResult = result;

            if (mSignInClicked) {
                processSignInError();
            }
        }

    }

    private void processSignInError() {
        if (mConnectionResult != null && mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(activity, SIGN_IN_REQUEST_GPLUS);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }


    @Override
    public void onResult(People.LoadPeopleResult loadPeopleResult) {

    }
}
