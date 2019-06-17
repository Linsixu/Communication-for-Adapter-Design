package cn.bili.linsixu.commen_base.view

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes

/**
 * Created by Magic
 * on 2019-06-17.
 * email: linsixu@bilibili.com
 */
data class ExpandParams(
    var iconWidth: Int = 0,
    var iconHeight: Int = 0,
    var iconBackground: Drawable? = null,
    var spaceNumber: Int = 0,
    var maxRows: Int = 0
)