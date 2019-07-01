package cn.bili.linsixu.module_main.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData

/**
 * Created by Magic
 * on 2019-07-01.
 * email: linsixu@bilibili.com
 */
enum class Status {
    LOADING, ERROR, SUCCESS
}

class LiveDataCallBack<out T> private constructor(
    val status: Status,
    val data: T? = null,
    val error: Throwable? = null) {
    companion object {
        @JvmStatic
        @JvmOverloads
        fun <T> loading(data: T? = null): LiveDataCallBack<T> {
            return LiveDataCallBack(status = Status.LOADING, data = data)
        }

        @JvmStatic
        @JvmOverloads
        fun <T> success(data: T? = null): LiveDataCallBack<T> {
            return LiveDataCallBack(status = Status.SUCCESS, data = data)
        }

        @JvmStatic
        @JvmOverloads
        fun <T> error(error: Throwable?): LiveDataCallBack<T> {
            return LiveDataCallBack(status = Status.ERROR, error = error)
        }
    }
}

typealias LiveCallBack<T> = LiveData<LiveDataCallBack<T>>
typealias MutableCallBack<T> = MutableLiveData<LiveDataCallBack<T>>
typealias MediatorCallBack<T> = MediatorLiveData<LiveDataCallBack<T>>