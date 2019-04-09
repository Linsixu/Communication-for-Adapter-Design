/*
 * bilibili:android studio write this method in 19-1-15 上午10:58
 */

package cn.bili.linsixu.module_main.vmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import cn.bili.linsixu.commen_base.utils.MyLog

/**
 * Created by Magic
 * on 2019/1/15.
 */
class RetrofitModel: LifecycleObserver,ActivityOnState{


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        MyLog.i("magic","onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        MyLog.i("magic","onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
        MyLog.i("magic","onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
        MyLog.i("magic","onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
        MyLog.i("magic","onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        MyLog.i("magic","onDestroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    override fun onAny() {
        MyLog.i("magic","onAny")
    }
}