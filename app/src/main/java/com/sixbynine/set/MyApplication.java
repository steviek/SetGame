package com.sixbynine.set;

import android.app.Application;

/**
 * Created by steviekideckel on 10/24/14.
 */
public class MyApplication extends Application{

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
