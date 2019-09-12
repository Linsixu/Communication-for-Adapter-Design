package cn.bili.linsixu.commen_base.utils

import android.app.Application
import android.content.Context
import android.net.Uri

/**
 * Created by Magic
 * on 2019-07-29.
 * email: linsixu@bilibili.com
 */
object ImageHelper {

    @JvmStatic
    @JvmOverloads
    fun dp2px(dp: Float, context: Context? = null): Int {
        return dp2px(dp, context?.resources?.displayMetrics?.density ?: 2f)
    }

    @JvmStatic
    fun dp2px(dp: Float, density: Float): Int {
        return (dp * density + 0.5f).toInt()
    }
}