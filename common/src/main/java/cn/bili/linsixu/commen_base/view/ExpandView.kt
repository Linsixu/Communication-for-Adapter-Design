package cn.bili.linsixu.commen_base.view

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import cn.bili.linsixu.commen_base.R
import java.lang.StringBuilder
import kotlin.math.max

/**
 * Created by Magic
 * on 2019-06-17.
 * email: linsixu@bilibili.com
 */
class ExpandView : View {
    private var mParams = ExpandViewParams()
    private val mPaint = Paint()

    private var maxRows = 0
    private var oneTextWidth = 0f
    private var textWidth = 0f
    private var spaceWidth = 0

    private val mDistance = 1f.toPx() //icon与字体之间的偏差
    private var mIconX = -1f
    private var mIconY = -1f
    private var mIconListener: OnIconClickListener? = null

    constructor(context: Context?) : super(context) {
        init(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    internal fun Float.toPx(): Int {
        return (this * (Resources.getSystem().displayMetrics?.density ?: 2f) + 0.5f).toInt()
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        val array = context?.obtainStyledAttributes(attrs, R.styleable.ExpandView)
        array?.let {
            val count = it.indexCount
            for (index in 0 until count) {
                mParams.mIcon = it.getDrawable(R.styleable.ExpandView_iconDrawable)
                mParams.mBackground = it.getDrawable(R.styleable.ExpandView_expandBackground)
                mParams.mMaxRows = it.getInt(R.styleable.ExpandView_maxRows, mParams.mMaxRows)
                mParams.mTextColor.mTextInitColor = it.getColor(R.styleable.ExpandView_textColor, mParams.mTextColor.mTextInitColor)
                mParams.mTextSize = it.getDimensionPixelSize(R.styleable.ExpandView_textSize, mParams.mTextSize)
                mParams.isNeedShowIcon = it.getBoolean(R.styleable.ExpandView_isNeedShowIcon, mParams.isNeedShowIcon)
                mParams.isNeedEllipsis = it.getBoolean(R.styleable.ExpandView_isNeedEllipsis, mParams.isNeedEllipsis)
                mParams.mTextInnerPadding = it.getDimensionPixelSize(R.styleable.ExpandView_textInnerPadding, mParams.mTextInnerPadding)
            }
            mParams.mTextColor.mTextShowColor = mParams.mTextColor.mTextInitColor
            mPaint.textSize = mParams.mTextSize.toFloat()
            mPaint.color = mParams.mTextColor.mTextShowColor
            mPaint.isAntiAlias = true
            mPaint.flags = Paint.ANTI_ALIAS_FLAG
            mPaint.style = Paint.Style.FILL
            it.recycle()

            spaceWidth = mPaint.measureText(SPACE_STRING).toInt()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val specModeOfWidth = MeasureSpec.getMode(widthMeasureSpec)
        val specWidth = MeasureSpec.getSize(widthMeasureSpec)
        val specModeOfHeight = MeasureSpec.getMode(heightMeasureSpec)
        val specHeight = MeasureSpec.getSize(heightMeasureSpec)

        var height = 0
        var width = caculateMyWidth(specModeOfWidth, specWidth)
        textWidth = mPaint.measureText(mParams.mTextContent.mTextInitContent)
        oneTextWidth = if (mParams.mTextContent.mTextInitContent.isNullOrBlank()) 0f else {
            textWidth / mParams.mTextContent.mTextInitContent!!.length
        }
        maxRows = (textWidth / width).toInt() + if (textWidth % width == 0f) 0 else 1

        if (mParams.mMaxRows != -1 && maxRows > mParams.mMaxRows) maxRows = mParams.mMaxRows
        if (specModeOfHeight == MeasureSpec.EXACTLY) {
            height = specHeight
        } else {
            height = (maxRows * caculateSingleTextHeight() + mParams.mTextInnerPadding * (maxRows - 1)).toInt()
            height += (paddingTop + paddingBottom)
            if (specModeOfHeight == MeasureSpec.AT_MOST && specHeight != 0) {
                height = Math.min(height, specHeight)
            }
        }
        setMeasuredDimension(width, height)
    }

    fun caculateMyWidth(specMode: Int, parentWidth: Int): Int {
        var width = 0
        if (specMode == MeasureSpec.EXACTLY) {
            //match_parent
            width = parentWidth
        } else {
            val textWidth = if (mParams.mTextContent.mTextInitContent.isNullOrBlank()) 0f else {
                mPaint.measureText(mParams.mTextContent.mTextInitContent)
            }
            width = textWidth.toInt()
            width += (paddingLeft + paddingRight)
            if (specMode == MeasureSpec.AT_MOST) {
                width = Math.min(textWidth.toInt(), parentWidth)
            }
        }
        return width
    }

    fun caculateSingleTextHeight(): Float {
        val fontMetrics = mPaint.fontMetrics
        return fontMetrics.descent - fontMetrics.ascent
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (oneTextWidth == 0f) return
        var drawableWidth = 0
        val maxSingleRowTextNumber = (width - paddingLeft - paddingRight) / oneTextWidth //一行最多有多少个字
        val font = mPaint.fontMetrics
        val list = mParams.mTextContent.mTextInitContent!!.spiltCount(mParams.mTextContent.mTextInitContent!!, maxSingleRowTextNumber.toInt())
        list?.let {
            for (i in 0 until it.size) {
                var y = i * (caculateSingleTextHeight() + mParams.mTextInnerPadding) - font.ascent
                if (i == 0) y += paddingTop
                if (mParams.mMaxRows != -1 && i + 1 > mParams.mMaxRows) break
                if (mParams.mMaxRows != -1 && i + 2 > mParams.mMaxRows) {
                    var deleteNumber = 0

                    //三点对应要删除多少个文字
                    var threePointWidth = 0f
                    var threePointReplaceNumber = 0
                    if (mParams.isNeedEllipsis) {
                        threePointWidth = mPaint.measureText(ELLIPSIS_STRING)
                        threePointReplaceNumber = (threePointWidth / oneTextWidth).toInt() + if (threePointWidth % oneTextWidth == 0f) 0 else 1
                    }

                    //icon+" "需要删除多少个文字
                    var iconReplaceNumber = 0
                    if (mParams.isNeedShowIcon) {
                        mParams.mIcon?.let { d ->
                            d.setBounds(0, 0, oneTextWidth.toInt(), caculateSingleTextHeight().toInt() - mDistance * 2)
                            iconReplaceNumber = 1 + (spaceWidth / oneTextWidth).toInt() + if (spaceWidth % oneTextWidth == 0f) 0 else 1
                        }
                    }
                    deleteNumber += (threePointReplaceNumber + iconReplaceNumber)
                    val stringBuilder = StringBuilder(list[i].substring(0, list[i].length - deleteNumber))
                    val initWidth = stringBuilder.toString().length * oneTextWidth
                    stringBuilder.append(ELLIPSIS_STRING)
                    onDrawText(canvas!!, y, stringBuilder.toString())
                    if (mParams.isNeedShowIcon && mParams.mIcon != null) {
                        mIconY = y + font.ascent + mDistance
                        mIconX = paddingLeft + initWidth + (deleteNumber - 1) * oneTextWidth
                        canvas.translate(mIconX, mIconY)
                        mParams.mIcon!!.draw(canvas)
                    }
                } else {
                    //icon需要删除多少个文字
                    var iconReplaceNumber = 1
                    if (mParams.isNeedShowIcon && i == it.size - 1) {
                        mParams.mIcon?.let { d ->
                            d.setBounds(0, 0, oneTextWidth.toInt(), caculateSingleTextHeight().toInt() - mDistance * 2)
                        }
                        var stringBuilder = StringBuilder(list[i].substring(0, it[i].length))
                        if (((it[i].length + iconReplaceNumber) * oneTextWidth).toInt() > width - paddingRight - paddingLeft) {
                            //添加icon超出宽度了
                            stringBuilder = StringBuilder(list[i].substring(0, it[i].length - iconReplaceNumber))
                        }
                        val initWidth = stringBuilder.toString().length * oneTextWidth
                        onDrawText(canvas!!, y, stringBuilder.toString())
                        if (mParams.isNeedShowIcon && mParams.mIcon != null) {
                            mIconY = y + font.ascent + mDistance
                            mIconX = paddingLeft + initWidth
                            canvas.translate(mIconX, mIconY)
                            mParams.mIcon!!.draw(canvas)
                        }
                    } else {
                        onDrawText(canvas!!, y, it[i])
                    }
                }
            }
        }
    }

    fun String.spiltCount(text: String, count: Int): List<String>? {
        if (text.length <= 0) return null
        val list = mutableListOf<String>()
        var startIndex = 0
        do {
            val result = if (startIndex + count > text.length) {
                text.subSequence(startIndex, text.length)
            } else {
                text.subSequence(startIndex, startIndex + count)
            }
            list.add(result.toString())
            startIndex += count
            if (startIndex > text.length) break
        } while (true)
        return list
    }

    fun onDrawText(canvas: Canvas, y: Float, text: String) {
        canvas.drawText(text, 0, text.length, paddingLeft.toFloat(), y, mPaint)
    }

    //与测量参数相关的变量改变后，都需要执行requestLayout
    fun setText(content: String) {
        mParams.mTextContent.mTextInitContent = content
        mParams.mTextContent.mTextShowContent = mParams.mTextContent.mTextInitContent
        requestLayout()
    }

    fun setEllipsis(isNeedEllipsis: Boolean) {
        mParams.isNeedEllipsis = isNeedEllipsis
        invalidate()
    }

    fun setTextInnerPadding(padding: Int) {
        mParams.mTextInnerPadding = padding
        requestLayout()
    }

    fun setMaxRows(maxRows: Int) {
        mParams.mMaxRows = maxRows
        requestLayout()
    }

    fun setIconDrawable(drawable: Drawable) {
        mParams.mIcon = drawable
    }

    fun setOnIconClickListener(listener: OnIconClickListener) {
        mIconListener = listener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (x > mIconX && x < mIconX + oneTextWidth
                    && y > mIconY && y < mIconY + caculateSingleTextHeight()) {
                    mIconListener?.onClick()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    companion object {
        val ELLIPSIS_NORMAL = charArrayOf('\u2026') // this is "..."
        val ELLIPSIS_STRING = String(ELLIPSIS_NORMAL)

        val SPACE_NORMAL = charArrayOf('\u0020')//this is " "
        val SPACE_STRING = String(SPACE_NORMAL)
    }

    //icon的点击事件
    interface OnIconClickListener {
        fun onClick()
    }
}