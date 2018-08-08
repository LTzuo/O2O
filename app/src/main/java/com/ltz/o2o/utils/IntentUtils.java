package com.ltz.o2o.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * 页面之间跳转工具
 * Created by 1 on 2018/1/24.
 */
public class IntentUtils {


    /**
     * 页面跳转（不带参数）
     * @param context
     * @param clzz
     */
    public static void Goto(Activity context, Class<?> clzz) {
        Intent intent = new Intent(context, clzz);
        context.startActivity(intent);
    }


//    /**
//     * 页面跳转（带参数）
//     * @param mIntent
//     */
//    public static void GotoWithParams(Activity context,Intent mIntent){
//        context.startActivity(mIntent);
////    }

}
