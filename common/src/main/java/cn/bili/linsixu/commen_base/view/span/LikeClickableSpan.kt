package cn.bili.linsixu.commen_base.view.span

import android.text.style.ClickableSpan
import android.view.View

/**
 * Created by Magic
 * on 2019-07-30.
 * email: linsixu@bilibili.com
 */
abstract class LikeClickableSpan<T>(val t: T) : ClickableSpan() {

    override fun onClick(widget: View?) {
        onDataClick(t)
    }

    abstract fun onDataClick(t: T)
}