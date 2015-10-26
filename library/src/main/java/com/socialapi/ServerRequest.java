package com.socialapi;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;

import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KARAN on 10-Jun-15.
 */
public class ServerRequest {

    private static Activity mActivity;
    private static ProgressBar mProgressBar;
    /*--------Error messages----------*/
    public static final String NO_INTERNET_CONNECTION="No Internet Connection";
    public static final String SERVER_ERROR="Server Error";
    public static final String NETWORK_ERROR="Network Error";
    public static final String UNKNOWN_ERROR="Unknown Error";
    public static final String CONNECTION_TIME_OUT="Connection Timeout";

    private ServerRequestListener mListener;
    private Context mContext;

    public ServerRequest(Activity mActivity, ServerRequestListener mListener) {
        this.mActivity = mActivity;
        this.mListener = mListener;
    }
    public ServerRequest(Fragment mFragment, ServerRequestListener mListener) {
        this.mActivity = mFragment.getActivity();
        this.mListener = mListener;
    }



    public void postConnection(String url, final Map<String, String> params,
                               final int reqId) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJsonString(response, reqId);

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError err) {
                if (err != null && err.getMessage() != null) {

                }

                mListener.onError(err);
                showError(err);
            }
        }) {

            protected Map<String, String> getParams()
                    throws AuthFailureError {
                Map<String, String> postParams = params;
                return postParams;
            }

            ;

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                return headers;
            }
        };

        ApplicationLoader.getInstance().addToRequestQueue(stringRequest, reqId + "");
    }



    private void parseJsonString(String response, int reqId) {

        dismissProgressBar();
        try {
            Gson gson = new Gson();

        }catch (Exception e){
            //if JSON parsing goes wrong
            e.printStackTrace();
        }


    }
    private void showError(VolleyError error) {
        //In your extended request class
        dismissProgressBar();
        if (error.networkResponse != null && error.networkResponse.data != null) {
            VolleyError volleyError = new VolleyError(new String(error.networkResponse.data));
            volleyError.printStackTrace();
        }
        if (error instanceof NetworkError) {
            showToast(NETWORK_ERROR);
        } else if (error instanceof ServerError) {
            showToast(SERVER_ERROR);
        } else if (error instanceof NoConnectionError) {
            showToast(NO_INTERNET_CONNECTION);
        } else if (error instanceof TimeoutError) {
            showToast(CONNECTION_TIME_OUT);
        } else {
            showToast(UNKNOWN_ERROR);
        }

    }

    private void showToast(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }



    public static void showProgressBar(ProgressBar progressBar) {
        try {
            mProgressBar = progressBar;
            mProgressBar.setVisibility(View.VISIBLE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void dismissProgressBar() {
        try {
            if (mProgressBar != null)
                mProgressBar.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
