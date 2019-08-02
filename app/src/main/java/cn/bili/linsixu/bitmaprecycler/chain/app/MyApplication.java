/*
 * bilibili:android studio write this method in 18-12-26 下午12:19
 */

package cn.bili.linsixu.bitmaprecycler.chain.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.bili.linsixu.bitmaprecycler.chain.app.name.DebugTipsDrawable;

import static cn.bili.linsixu.bitmaprecycler.BuildConfig.Debug;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();     // Print log
        ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(this);
        if (Debug) this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallback());
    }

    @SuppressLint("NewApi")
    class MockPegasusFragmentLifecycleCallback extends FragmentManager.FragmentLifecycleCallbacks {
        @Override
        public void onFragmentViewCreated(
                FragmentManager fm,
                Fragment f,
                View v,
                Bundle savedInstanceState
        ) {
            //为fragment的左上角添加名称
            v.getOverlay().add(new DebugTipsDrawable(f.getClass().getSimpleName(), Color.GREEN, false));
        }

        @Override
        public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
            if (f.getView() != null) f.getView().getOverlay().clear();
        }
    }

    class ActivityLifecycleCallback implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                activity.getWindow().getDecorView().getOverlay().add(
                        new DebugTipsDrawable(activity.getComponentName().getClassName(),
                                Color.RED,
                                true)
                );
                activity.getWindow().getDecorView().invalidate();
                if (activity instanceof FragmentActivity) {
                    ((FragmentActivity) activity).getSupportFragmentManager()
                            .registerFragmentLifecycleCallbacks(
                                    new MockPegasusFragmentLifecycleCallback(), true);
                }
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
