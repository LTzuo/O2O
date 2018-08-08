package com.ltz.o2o.app;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.ltz.o2o.imageloader.GlideAlbumLoader;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;

import net.nashlegend.anypref.AnyPref;

import org.litepal.LitePal;

import java.util.Locale;

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
        //初始化SharedPreferences工具
        AnyPref.init(this);

        //初始化图片选择器
        Album.initialize(
                AlbumConfig.newBuilder(this)
                        .setAlbumLoader(new GlideAlbumLoader()) // 设置Album加载器。
                        .setLocale(Locale.CHINA) // 比如强制设置在任何语言下都用中文显示。
                        .build()
        );
    }


    public static App getInstance() {
        return mInstance;
    }

}