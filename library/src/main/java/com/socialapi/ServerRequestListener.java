package com.socialapi;


import com.android.volley.VolleyError;

/**
 * Created by Nishant on 23-Dec-14.
 */
public interface ServerRequestListener {

    /**
     *
     * @param obj
     * : Object of Pojo class
     * @param tag
     * : Request id Which is used to initiate volley request.
     */
    <T> void onResponse(T obj, int tag);

    /**
     *
     * for failure handling
     * @param error
     */
    void onError(VolleyError error);
}
