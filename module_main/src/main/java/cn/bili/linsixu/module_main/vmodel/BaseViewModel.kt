package cn.bili.linsixu.module_main.vmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import android.support.v4.app.FragmentActivity
import cn.bili.linsixu.commen_base.utils.MyLog

/**
 * Created by Magic
 * on 2019/2/27.
 * email: linsixu@bilibili.com
 */
abstract class BaseViewModel<T> : ViewModel(), LifecycleObserver, ActivityOnState {

    abstract fun getModelContext(context : FragmentActivity):FragmentActivity

    abstract fun setModelContext()

    abstract fun currentModelName(): BaseViewModel<T>

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

//    fun getCurrentModel(context: FragmentActivity,t: BaseViewModel<T>): T{
//        return ViewModelProviders.of(context).get(t.javaClass)
//    }
//
//    fun getCurrentModel(context: Fragment, t: BaseViewModel<T>): T{
//        return ViewModelProviders.of(context).get(t::class.java)
//    }
}