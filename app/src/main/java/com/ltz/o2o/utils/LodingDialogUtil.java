package com.ltz.o2o.utils;

import android.app.Activity;

import dmax.dialog.SpotsDialog;

/**
 * 加载中对话框工具
 * Created by 1 on 2018/3/28.
 */
public class LodingDialogUtil {

    private static SpotsDialog lodingdialog;

    public static void showLoding(Activity context) {
        if (lodingdialog == null) {
            lodingdialog = new SpotsDialog(context);
        }
        lodingdialog.show();
    }

    public static void dissdialog() {
        if (lodingdialog != null) {
            lodingdialog.dismiss();
        }
        lodingdialog = null;
    }

}
