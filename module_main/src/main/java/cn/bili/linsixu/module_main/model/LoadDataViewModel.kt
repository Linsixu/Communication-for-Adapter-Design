package cn.bili.linsixu.module_main.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import cn.bili.linsixu.module_main.bean.LiveDataBean

/**
 * Created by Magic
 * on 2019-07-01.
 * email: linsixu@bilibili.com
 */
class LoadDataViewModel : ViewModel() {
    val dataTest: MutableCallBack<LiveDataBean> = MutableCallBack()

    companion object {
        @JvmStatic
        fun getViewModel(activity: FragmentActivity?): LoadDataViewModel? {
            return if (activity == null) {
                null
            } else {
                ViewModelProviders.of(activity).get(LoadDataViewModel::class.java)
            }
        }
    }

    fun loadingData(data: LiveDataBean?) {
        dataTest.value = LiveDataCallBack.loading(data)
    }

    fun successData(data: LiveDataBean?) {
        dataTest.value = LiveDataCallBack.success(data)
    }

    fun errorData(error: Throwable) {
        dataTest.value = LiveDataCallBack.error(error)
    }
}