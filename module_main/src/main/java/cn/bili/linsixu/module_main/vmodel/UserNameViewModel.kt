package cn.bili.linsixu.module_main.vmodel

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import cn.bili.linsixu.commen_base.utils.MyLog
import cn.bili.linsixu.module_main.bean.ListNotifyEvent
import cn.bili.linsixu.module_main.bean.UserName
import cn.bili.linsixu.module_main.event.SingleLiveEvent
import cn.bili.linsixu.module_main.vmodel.helper.ListDataNotifyHelper
import java.util.*

/**
 * Created by Magic
 * on 2019/2/27.
 * email: linsixu@bilibili.com
 */
class UserNameViewModel : ViewModel() {

    val mClickUpdateMsg = SingleLiveEvent<Any>()

    var mListNotify = MutableLiveData<ListNotifyEvent>()

    companion object {

        @JvmStatic
        fun getListUser(activity: Activity?): MutableLiveData<ListNotifyEvent>? {
            return getCurrentModel(activity)?.mListNotify
        }

        @JvmStatic
        fun updateListNotifyEvent(activity: Activity?, newEvent: ListNotifyEvent) {
            getCurrentModel(activity)?.mListNotify?.value = newEvent
        }

        /**
         * 点击事件
         */
        @JvmStatic
        fun clickUpdateMsg(activity: Activity?, position: Int) {
            getCurrentModel(activity)?.mClickUpdateMsg?.call()
        }

        @JvmStatic
        fun observeClickUpdate(activity: Activity?, observer: Observer<Any>) {
            if (activity is FragmentActivity) {
                getCurrentModel(activity)?.mClickUpdateMsg?.observe(activity, observer)
            }
        }

        @JvmStatic
        fun observeListDataSource(activity: Activity?, observer: Observer<ListNotifyEvent>) {
            if (activity is FragmentActivity) {
                getCurrentModel(activity)?.mListNotify?.observe(activity, observer)
            }
        }

        @JvmStatic
        fun getCurrentModel(activity: Activity?): UserNameViewModel? {
            if (activity is FragmentActivity) {
                return ViewModelProviders.of(activity).get(UserNameViewModel::class.java)
            }
            return null
        }
    }
}