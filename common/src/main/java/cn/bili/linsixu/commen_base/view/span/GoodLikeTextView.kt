package cn.bili.linsixu.commen_base.view.span

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.widget.TextView
import cn.bili.linsixu.commen_base.R
import cn.bili.linsixu.commen_base.utils.ImageHelper.dp2px

/**
 * Created by Magic
 * on 2019-07-29.
 * email: linsixu@bilibili.com
 */
class GoodLikeTextView : TextView {
    private val mParams = TextViewParams()
    private val mutableList = mutableListOf<Data>()
    private val mLinePaint = Paint()
    private val mTextPaint = Paint()
    private val mText = "我的人健康水淀粉即可缓解咳嗽粉即可缓解咳嗽"
    private val mText2 = "我的人健康水淀粉即可缓解咳嗽粉即可缓解咳嗽"

    companion object {
        val ELLIP_TEXT = "..."
        val BETWEEN_DASH = "、"
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val array = context.obtainStyledAttributes(attrs, R.styleable.GoodLikeTextView)
            for (i in 0 until array.length()) {
                val index = array.getIndex(i)
                when (index) {
                    R.styleable.GoodLikeTextView_likeIconDrawable -> mParams.mIcon = array.getDrawable(index)
                    R.styleable.GoodLikeTextView_nameTextColor -> mParams.mNameTextColor = array.getColor(index, mParams.mNameTextColor)
                    R.styleable.GoodLikeTextView_nameTextNightColor -> mParams.mNameTextNightColor = array.getColor(index, mParams.mNameTextNightColor)
                    R.styleable.GoodLikeTextView_contentTextColor -> mParams.mContentTextColor = array.getColor(index, mParams.mContentTextColor)
                    R.styleable.GoodLikeTextView_contentTextNightColor -> mParams.mContentTextNightColor = array.getColor(index, mParams.mContentTextNightColor)
                    R.styleable.GoodLikeTextView_isNeedLikeEllipsis -> mParams.isNeedEllipsis = array.getBoolean(index, mParams.isNeedEllipsis)
                    R.styleable.GoodLikeTextView_maxLines -> mParams.maxLines = array.getInteger(index, mParams.maxLines)
                    R.styleable.GoodLikeTextView_iconAndTextPadding -> mParams.mIconAndTextPadding = array.getDimensionPixelSize(index, mParams.mIconAndTextPadding)
                }
            }
            array.recycle()
        }
        initPaint()
    }

    private fun initPaint() {
        mLinePaint.isAntiAlias = true
        mLinePaint.strokeWidth = dp2px(14f, this.context).toFloat()
        mLinePaint.color = Color.GREEN
        mLinePaint.strokeCap = Paint.Cap.ROUND

        mTextPaint.isAntiAlias = true
        mTextPaint.textSize = mParams.mNameTextSize.toFloat()
        mTextPaint.color = Color.BLUE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        val parentWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val parentHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        val myWidth = onMeasureWidth(parentWidth, parentWidthMode)
        val myHeight = onMeasureHeight(parentHeight, parentHeightMode)
        setMeasuredDimension(myWidth, myHeight)
    }

    private fun onMeasureWidth(parentWidth: Int, parentWidthMode: Int): Int {
        return if (parentWidthMode == MeasureSpec.EXACTLY) {
            val width = mTextPaint.measureText(mText)
            parentWidth
        } else {
            parentWidth
        }
    }

    private fun onMeasureHeight(parentHeight: Int, parentHeightMode: Int): Int {
        return if (parentHeightMode == MeasureSpec.EXACTLY) {
            parentHeight
        } else {
            parentHeight
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            onDrawLines(it)
        }
    }

    private fun onDrawLines(canvas: Canvas) {
        val test = dp2px(14f, this.context).toFloat()
        canvas.drawLine(0f, test, 0f, height.toFloat() - test, mLinePaint)
    }

    data class Data(
        var url: String? = null,
        var upName: String? = null
    )

    data class TextViewParams(
        var mIcon: Drawable? = null,
        var mNameTextSize: Int = 10,
        @ColorInt
        var mNameTextColor: Int = -1,
        @ColorInt
        var mNameTextNightColor: Int = -1,
        var mContentTextSize: Int = 10,
        @ColorInt
        var mContentTextColor: Int = -1,
        @ColorInt
        var mContentTextNightColor: Int = -1,
        var mIconAndTextPadding: Int = 0,
        var isNeedEllipsis: Boolean = true,
        var maxLines: Int = 1
    )
}