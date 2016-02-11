package com.example.abedx.docbook;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by abedx on 2/10/2016.
 */
public class APP_CONFIG extends Application {
    private static APP_CONFIG mInstance;
    private static RequestQueue mRequest;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public synchronized APP_CONFIG getInstance() {
        return mInstance;

    }
    public RequestQueue getRequest()
    {
        if(mRequest==null)
        {
            mRequest= Volley.newRequestQueue(getApplicationContext());
        }
        return mRequest;
    }
}
