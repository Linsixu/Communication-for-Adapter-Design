package cn.bili.linsixu.module_main.vmodel.helper

import android.app.Activity
import android.support.v4.app.FragmentActivity
import cn.bili.linsixu.module_main.bean.ListNotifyEvent
import cn.bili.linsixu.module_main.vmodel.UserNameViewModel

/**
 * Created by Magic
 * on 2019/2/28.
 * email: linsixu@bilibili.com
 */
object ListDataNotifyHelper {

    @JvmStatic
    fun <T> addAll(activity: Activity?, sourceList: List<T>, target: List<T>) {
        if (activity !is FragmentActivity) {
            return
        }
        if (sourceList is ArrayList<T>) {
            val insertIndex = sourceList.size
            sourceList.addAll(target)
            val position = UserNameViewModel.getCurrentModel(activity)?.mListNotify
            position?.value = ListNotifyEvent(insertIndex + 1,
                target.size, ListNotifyEvent.Notify.ADDALL)
        }
    }

    @JvmStatic
    fun <T> add(activity: Activity?, sourceList: List<T>?, value: T) {
        if (activity !is FragmentActivity) {
            return
        }
        if (sourceList is ArrayList<T>) {
            val insertIndex = sourceList.size
            sourceList.add(value)
            val position = UserNameViewModel.getCurrentModel(activity)?.mListNotify
            position?.value = ListNotifyEvent(insertIndex + 1, 1
                , ListNotifyEvent.Notify.ADD)
        }
    }

    @JvmStatic
    fun <T> remove(activity: Activity?, sourceList: List<T>, value: T) {
        if (activity !is FragmentActivity) {
            return
        }
        if (sourceList is ArrayList<T>) {
            val insertIndex = sourceList.indexOf(value)
            val position = UserNameViewModel.getCurrentModel(activity)?.mListNotify
            position?.value = ListNotifyEvent(insertIndex + 1, 1
                , ListNotifyEvent.Notify.DELETE)
            sourceList.remove(value)
        }
    }

    @JvmStatic
    fun <T> updateValue(activity: Activity?, sourceList: List<T>, value: T, po: Int) {
        if (activity !is FragmentActivity) {
            return
        }
        if (sourceList is ArrayList<T>) {
            val position = UserNameViewModel.getCurrentModel(activity)?.mListNotify
            position?.value = ListNotifyEvent(po + 1, 1
                , ListNotifyEvent.Notify.UPDATE)
            sourceList.set(po, value)
        }
    }

    @JvmStatic
    fun <T> insertValue(activity: Activity?, sourceList: List<T>, value: T, index: Int){
        if (activity !is FragmentActivity) {
            return
        }
        if (sourceList is ArrayList<T>) {
            val position = UserNameViewModel.getCurrentModel(activity)?.mListNotify
            position?.value = ListNotifyEvent(index + 1, 1
                , ListNotifyEvent.Notify.INSERT)
            sourceList.add(index, value)
        }
    }
}