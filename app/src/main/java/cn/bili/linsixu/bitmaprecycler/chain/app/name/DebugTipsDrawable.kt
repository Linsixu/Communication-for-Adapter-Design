package cn.bili.linsixu.bitmaprecycler.chain.app.name

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.text.TextPaint

/**
 * Created by Magic
 * on 2019-08-02.
 * email: linsixu@bilibili.com
 */
class DebugTipsDrawable(
    val name: String,
    textColor: Int = Color.RED,
    val alignToEnd: Boolean = false
) : Drawable() {
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 24F
        color = textColor
    }

    private val color: Int = 0x8fffffff.toInt()
    private val baseLine: Float

    private val back: RectF

    init {
        back = RectF(0f, 0f, textPaint.measureText(name) + 4, textPaint.fontMetrics.let { it.bottom - it.top } + 4)
        baseLine = back.centerY() - (textPaint.ascent() + textPaint.descent()) / 2
    }

    override fun setAlpha(alpha: Int) {}

    override fun getAlpha(): Int {
        return color ushr 24
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    override fun draw(canvas: Canvas) {
        canvas.run {
            val i = save()
            try {
                val b = back
                if (alignToEnd) {
                    clipBounds.let { translate(it.right - b.width() - 8, 0f) }
                }
                clipRect(b)
                drawColor(color)
                drawText(name, b.left + 2, baseLine, textPaint)
            } finally {
                restoreToCount(i)
            }
        }
    }
}
