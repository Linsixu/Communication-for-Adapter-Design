package cn.bili.linsixu.bitmaprecycler.chain.utils;


import android.util.Log;

import cn.bili.linsixu.bitmaprecycler.BuildConfig;

/**
 * Created by Magic
 * on 2018/10/10.
 */
public class MyLog {

    public static boolean isDebug = BuildConfig.Debug;

    public static void i(String className,String content){
        if(isDebug){
            Log.i("magic",content);
        }
    }
}
