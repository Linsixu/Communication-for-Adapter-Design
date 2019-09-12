package cn.bili.linsixu.commen_base.view

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import cn.bili.linsixu.commen_base.R
import java.lang.StringBuilder
import kotlin.math.max
import kotlin.math.min

/**
 * Created by Magic
 * on 2019-06-17.
 * 出现英文就不行了
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
    private var mPoint: PointF = PointF()
    private var mIconListener: OnIconClickListener? = null

    private var specWidth: Int = 0
    private var specHeight: Int = 0
    private var mNeedAddLineNumber = 0

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
                val flag = it.getIndex(index)
                when (flag) {
                    R.styleable.ExpandView_iconDrawable -> mParams.mIcon = it.getDrawable(flag)
                    R.styleable.ExpandView_expandBackground -> mParams.mBackground = it.getDrawable(flag)
                    R.styleable.ExpandView_maxRows -> mParams.mMaxRows = it.getInt(flag, mParams.mMaxRows)
                    R.styleable.ExpandView_textColor -> mParams.mTextColor.mTextInitColor = it.getColor(flag, mParams.mTextColor.mTextInitColor)
                    R.styleable.ExpandView_textSize -> mParams.mTextSize = it.getDimensionPixelSize(flag, mParams.mTextSize)
                    R.styleable.ExpandView_isNeedShowIcon -> mParams.isNeedShowIcon = it.getBoolean(flag, mParams.isNeedShowIcon)
                    R.styleable.ExpandView_isNeedEllipsis -> mParams.isNeedEllipsis = it.getBoolean(flag, mParams.isNeedEllipsis)
                    R.styleable.ExpandView_textInnerPadding -> mParams.mTextInnerPadding = it.getDimensionPixelSize(flag, mParams.mTextInnerPadding)
                    R.styleable.ExpandView_isNeedSpace -> mParams.isNeedSpaceBetweenIcon = it.getBoolean(flag, mParams.isNeedSpaceBetweenIcon)
                }
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
        specWidth = MeasureSpec.getSize(widthMeasureSpec)
        val specModeOfHeight = MeasureSpec.getMode(heightMeasureSpec)
        specHeight = MeasureSpec.getSize(heightMeasureSpec)
        var width = caculateMyWidth(specModeOfWidth, specWidth)
        var height = 0

        textWidth = mPaint.measureText(mParams.mTextContent.mTextInitContent)
        oneTextWidth = if (mParams.mTextContent.mTextInitContent.isNullOrBlank()) 0f else {
            textWidth / mParams.mTextContent.mTextInitContent!!.length
        }
        maxRows = (textWidth / width).toInt() + if (textWidth % width == 0f) 0 else 1

        if (mParams.mMaxRows != -1 && maxRows > mParams.mMaxRows) maxRows = mParams.mMaxRows
        if (specModeOfHeight == MeasureSpec.EXACTLY) {
            height = specHeight
        } else {
            height = ((maxRows + mNeedAddLineNumber) * caculateSingleTextHeight() + mParams.mTextInnerPadding * (maxRows + mNeedAddLineNumber - 1)).toInt()
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
        if (mParams.isNeedEllipsis && mParams.isNeedShowIcon && mParams.isNeedSpaceBetweenIcon) {
            //同时绘制三点，空格，icon
            drawAllDecoration(canvas!!, font, list)
        } else if (!mParams.isNeedEllipsis && mParams.isNeedShowIcon && mParams.isNeedSpaceBetweenIcon) {
            //同时绘制空格，icon
            drawSpaceAndIcon(canvas!!, font, list)
        } else if (!mParams.isNeedEllipsis && mParams.isNeedShowIcon && !mParams.isNeedSpaceBetweenIcon) {
            //只绘制icon
            onlyDrawIcon(canvas!!, font, list)
        } else if (mParams.isNeedEllipsis && !mParams.isNeedShowIcon && !mParams.isNeedSpaceBetweenIcon) {
            onlyDrawEllipsis(canvas!!, font, list)
        }
    }

    fun onlyDrawEllipsis(canvas: Canvas, font: Paint.FontMetrics, contents: List<String>?) {
        contents?.let {
            for (i in 0 until it.size) {
                var y = i * (caculateSingleTextHeight() + mParams.mTextInnerPadding) - font.ascent
                if (i == 0) y += paddingTop
                if (mParams.mMaxRows != -1 && i + 1 > mParams.mMaxRows) break
                if (mParams.mMaxRows != -1 && i + 2 > mParams.mMaxRows) {
                    var deleteNumber = 0
                    //三点对应要删除多少个文字
                    if (mParams.isNeedEllipsis) {
                        deleteNumber = mPaint.measureText(ELLIPSIS_STRING).toInt()
                        deleteNumber = (deleteNumber / oneTextWidth).toInt() + if (deleteNumber % oneTextWidth == 0f) 0 else 1
                    }
                    val stringBuilder = StringBuilder(contents[i].substring(0, contents[i].length - deleteNumber))
                    appendEllipsis(stringBuilder)
                    onDrawText(canvas, y, stringBuilder.toString())
                } else {
                    onDrawText(canvas, y, it[i])
                }
            }
        }
    }

    //finish
    fun onlyDrawIcon(canvas: Canvas, font: Paint.FontMetrics, contents: List<String>?) {
        contents?.let {
            //超过最大行数
            for (i in 0 until it.size) {
                val minRow = min(it.size - 1, mParams.mMaxRows - 1)
                var y = i * (caculateSingleTextHeight() + mParams.mTextInnerPadding) - font.ascent
                if (i == 0) y += paddingTop
                if (mParams.mMaxRows != -1 && i + 1 > mParams.mMaxRows) break
                //icon需要删除多少个文字
                var iconReplaceNumber = 1
                if (mParams.isNeedShowIcon && i == minRow) {
                    mParams.mIcon?.let { d ->
                        d.setBounds(0, 0, oneTextWidth.toInt(), caculateSingleTextHeight().toInt() - mDistance * 2)
                    }
                    var stringBuilder = StringBuilder(contents[i].substring(0, it[i].length))
                    if (((it[i].length + iconReplaceNumber) * oneTextWidth).toInt() > width - paddingRight - paddingLeft) {
                        //添加icon超出宽度了
                        if (i < mParams.mMaxRows - 1) {
                            //如果小于最大行数，则icon位于下一行
                            mNeedAddLineNumber = 1
                            requestLayout()
                            onDrawText(canvas, y, stringBuilder.toString())
                            if (mParams.isNeedShowIcon && mParams.mIcon != null) {
                                mPoint.y = y + mDistance + mParams.mTextInnerPadding
                                mPoint.x = paddingLeft.toFloat()
                                drawIcon(canvas, mPoint.x, mPoint.y)
                            }
                        } else {
                            stringBuilder = StringBuilder(contents[i].substring(0, it[i].length - iconReplaceNumber))
                            val initWidth = stringBuilder.toString().length * oneTextWidth
                            onDrawText(canvas, y, stringBuilder.toString())
                            if (mParams.isNeedShowIcon && mParams.mIcon != null) {
                                mPoint.y = y + font.ascent + mDistance
                                mPoint.x = paddingLeft + initWidth
                                drawIcon(canvas, mPoint.x, mPoint.y)
                            }
                        }
                    } else {
                        //不超出一行，直接绘制到文字后面
                        val initWidth = stringBuilder.toString().length * oneTextWidth
                        onDrawText(canvas, y, stringBuilder.toString())
                        if (mParams.isNeedShowIcon && mParams.mIcon != null) {
                            mPoint.y = y + font.ascent + mDistance
                            mPoint.x = paddingLeft + initWidth
                            drawIcon(canvas, mPoint.x, mPoint.y)
                        }
                    }
                } else {
                    onDrawText(canvas, y, it[i])
                }
            }
        }
    }

    //finish
    fun drawSpaceAndIcon(canvas: Canvas, font: Paint.FontMetrics, contents: List<String>?) {
        contents?.let {
            //超过最大行数
            for (i in 0 until it.size) {
                val minRow = min(it.size - 1, mParams.mMaxRows - 1)
                var y = i * (caculateSingleTextHeight() + mParams.mTextInnerPadding) - font.ascent
                if (i == 0) y += paddingTop
                if (mParams.mMaxRows != -1 && i + 1 > mParams.mMaxRows) break
                //icon需要删除多少个文字
                var iconReplaceNumber = 0
                if (mParams.isNeedShowIcon && i == minRow) {
                    mParams.mIcon?.let { d ->
                        d.setBounds(0, 0, oneTextWidth.toInt(), caculateSingleTextHeight().toInt() - mDistance * 2)
                    }
                    iconReplaceNumber = 1 + (spaceWidth / oneTextWidth).toInt() + if (spaceWidth % oneTextWidth == 0f) 0 else 1
                    var stringBuilder = StringBuilder(contents[i].substring(0, it[i].length))
                    if (((it[i].length + iconReplaceNumber) * oneTextWidth).toInt() > width - paddingRight - paddingLeft) {
                        //添加icon超出宽度了
                        if (i < mParams.mMaxRows - 1) {
                            //如果小于最大行数，则icon位于下一行
                            mNeedAddLineNumber = 1
                            requestLayout()
                            onDrawText(canvas, y, stringBuilder.toString())
                            if (mParams.isNeedShowIcon && mParams.mIcon != null) {
                                mPoint.y = y + mDistance + mParams.mTextInnerPadding
                                mPoint.x = paddingLeft.toFloat()
                                drawIcon(canvas, mPoint.x, mPoint.y)
                            }
                        } else {
                            stringBuilder = StringBuilder(contents[i].substring(0, it[i].length - iconReplaceNumber))
                            val initWidth = stringBuilder.toString().length * oneTextWidth
                            onDrawText(canvas, y, stringBuilder.toString())
                            if (mParams.isNeedShowIcon && mParams.mIcon != null) {
                                mPoint.y = y + font.ascent + mDistance
                                mPoint.x = paddingLeft + initWidth + (iconReplaceNumber - 1) * oneTextWidth
                                drawIcon(canvas, mPoint.x, mPoint.y)
                            }
                        }
                    } else {
                        //不超出一行，直接绘制到文字后面
                        val initWidth = stringBuilder.toString().length * oneTextWidth
                        onDrawText(canvas, y, stringBuilder.toString())
                        if (mParams.isNeedShowIcon && mParams.mIcon != null) {
                            mPoint.y = y + font.ascent + mDistance
                            mPoint.x = paddingLeft + initWidth + (iconReplaceNumber - 1) * oneTextWidth
                            drawIcon(canvas, mPoint.x, mPoint.y)
                        }
                    }
                } else {
                    onDrawText(canvas, y, it[i])
                }
            }
        }
    }

    fun drawAllDecoration(canvas: Canvas, font: Paint.FontMetrics, contents: List<String>?) {
        contents?.let {
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
                    val stringBuilder = StringBuilder(contents[i].substring(0, contents[i].length - deleteNumber))
                    val initWidth = stringBuilder.toString().length * oneTextWidth
                    appendEllipsis(stringBuilder)
                    onDrawText(canvas, y, stringBuilder.toString())
                    if (mParams.isNeedShowIcon && mParams.mIcon != null) {
                        mPoint.y = y + font.ascent + mDistance
                        mPoint.x = paddingLeft + initWidth + (deleteNumber - 1) * oneTextWidth
                        canvas.translate(mPoint.x, mPoint.y)
                        mParams.mIcon!!.draw(canvas)
                    }
                } else {
                    onDrawText(canvas, y, it[i])
                }
            }
        }
    }

    fun drawIcon(canvas: Canvas, x: Float, y: Float) {
        canvas.translate(x, y)
        mParams.mIcon?.draw(canvas)
    }

    fun appendEllipsis(content: StringBuilder): StringBuilder {
        return content.append(ELLIPSIS_STRING)
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

    fun setSpaceNeed(isNeedSpace: Boolean) {
        mParams.isNeedSpaceBetweenIcon = isNeedSpace
        invalidate()
    }

    fun setShowIcon(isNeedShowIcon: Boolean) {
        mParams.isNeedShowIcon = isNeedShowIcon
        invalidate()
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
                if (x > mPoint.x && x < mPoint.x + oneTextWidth
                    && y > mPoint.y && y < mPoint.y + caculateSingleTextHeight()) {
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