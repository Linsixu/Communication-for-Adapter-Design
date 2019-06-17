package cn.bili.linsixu.commen_base.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan

/**
 * Created by Magic
 * on 2019-05-28.
 * email: linsixu@bilibili.com
 */
class ThreePointImageSpan: ImageSpan {
    constructor(context: Context?, resourceId: Int) : super(context, resourceId)

    fun getDrawable(h: Int): Drawable {
        var d = super.getDrawable()
        d = zoomDrawable(d, h, h)
        d.setBounds(0, 0, h, h - 10)
        return d
    }

    private fun zoomDrawable(drawable: Drawable, w: Int, h: Int): Drawable {
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        val oldbmp = drawableToBitmap(drawable)
        val matrix = Matrix()
        val scaleWidth = w.toFloat() / width
        val scaleHeight = h.toFloat() / height
        matrix.postScale(scaleWidth, scaleHeight)
        val newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
            matrix, true)
        return BitmapDrawable(null, newbmp)
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        val config = if (drawable.opacity != PixelFormat.OPAQUE)
            Bitmap.Config.ARGB_8888
        else
            Bitmap.Config.RGB_565
        val bitmap = Bitmap.createBitmap(width, height, config)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
        return bitmap
    }

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int,
                         fm: Paint.FontMetricsInt?): Int {
        val threePointWidth = paint.measureText(ELLIPSIS_STRING).toInt()
        val fmPaint = paint.fontMetricsInt
        val d = drawable
        val rect = d.bounds
        if (fm != null) {
            val fontHeight = fmPaint.bottom - fmPaint.top
            val drHeight = rect.bottom - rect.top

            val top = drHeight / 2 - fontHeight / 4
            val bottom = drHeight / 2 + fontHeight / 4

            fm.ascent = -bottom
            fm.top = -bottom
            fm.bottom = top
            fm.descent = top
        }
        return rect.right
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int,
                      x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {

        canvas.save()
        var transY = 0
        val fm = paint.fontMetricsInt
        val b = getDrawable(fm.descent - fm.ascent)
        transY = (y + fm.descent + y + fm.ascent) / 2 - b.bounds.bottom / 2
        canvas.translate(x, transY.toFloat())
        b.draw(canvas)
        canvas.restore()
    }

    companion object{
        val ELLIPSIS_NORMAL = charArrayOf('\u2026') // this is "..."
        val ELLIPSIS_STRING = String(ELLIPSIS_NORMAL)
    }
}