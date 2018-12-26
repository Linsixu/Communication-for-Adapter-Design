/*
 * bilibili:android studio write this method in 18-12-26 下午12:19
 */

package cn.bili.linsixu.bitmaprecycler.chain.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import static cn.bili.linsixu.bitmaprecycler.chain.utils.MyLog.isDebug;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();     // Print log
        ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(this);
    }
}
