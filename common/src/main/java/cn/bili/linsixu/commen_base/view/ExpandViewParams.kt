package cn.bili.linsixu.commen_base.view

import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt

/**
 * Created by Magic
 * on 2019-06-17.
 * email: linsixu@bilibili.com
 */
data class ExpandViewParams(
    var mTextSize: Int = 0,
    var mTextColor: ExpandTextColor = ExpandTextColor(),
    var mIcon: Drawable? = null,
    var isNeedEllipsis: Boolean = false,
    var isNeedShowIcon: Boolean = false,
    var mMaxRows: Int = -1,
    var mBackground: Drawable? = null,
    var mTextInnerPadding: Int = 0,
    var isNeedSpaceBetweenIcon: Boolean = false,

    var mTextContent: ExPandTextContent = ExPandTextContent()
)

data class ExpandTextColor(
    @ColorInt
    var mTextInitColor: Int = 0,
    @ColorInt
    var mTextShowColor: Int = 0
)

data class ExPandTextContent(
    var mTextInitContent: String? = null,
    var mTextShowContent: String? = null
)