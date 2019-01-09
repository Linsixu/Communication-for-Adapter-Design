/*
 * bilibili:android studio write this method in 19-1-9 上午11:34
 */

package cn.bili.linsixu.commen_base.utils;


import android.util.Log;

import cn.bili.linsixu.commen_base.BuildConfig;


public class MyLog {

    public static boolean isDebug = BuildConfig.DEBUG;

    public static void i(String className,String content){
        if(isDebug){
            Log.i("magic",content);
        }
    }
}
