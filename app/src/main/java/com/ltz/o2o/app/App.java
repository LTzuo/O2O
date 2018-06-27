package com.ltz.o2o.app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import org.litepal.LitePal;

/**
 * Created by 1 on 2018/1/15.
 */
public class App extends Application {

    public static App mInstance;

    public static Handler mAppHandler = new Handler(Looper.myLooper());

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //初始化数据库
        LitePal.initialize(this);
    }


    public static App getInstance() {
        return mInstance;
    }

}