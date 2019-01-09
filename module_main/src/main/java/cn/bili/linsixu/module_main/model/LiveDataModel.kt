/*
 * bilibili:android studio write this method in 19-1-8 下午8:15
 */

package cn.bili.linsixu.module_main.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import java.util.*
import kotlin.concurrent.timer

/**
 * Created by Magic
 * on 2019/1/8.
 */
public class LiveDataModel : ViewModel() {

    var mElapsedTime: MutableLiveData<Long> = MutableLiveData()

    var mInitialTime: Long = 0

    companion object {
        val ONE_SECOND: Long = 1000
    }

    init {
        mInitialTime = SystemClock.elapsedRealtime()

        var timer: Timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                var newValue: Long = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND, ONE_SECOND)
    }
}