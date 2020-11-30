package com.liliang.mylibrary.utils;

import android.graphics.drawable.Drawable;

import androidx.annotation.StringRes;

import com.liliang.mylibrary.MyApplication;


/**
 * Created by liliang on 2018/3/16 14:11
 */
public class ResourceUtils {

    public static Drawable getDrawable(int id) {
        return MyApplication.getInstance().getResources().getDrawable(id);
    }

    public static int getColor(int id) {
        return MyApplication.getInstance().getResources().getColor(id);
    }

    public static String getString(@StringRes int id) {
        return MyApplication.getInstance().getResources().getString(id);
    }

    public static String getString(@StringRes int id, Object... formatArgs) {
        return MyApplication.getInstance().getResources().getString(id, formatArgs);
    }

    public static int getDimen(int id) {
        return MyApplication.getInstance().getResources().getDimensionPixelOffset(id);
    }

}
