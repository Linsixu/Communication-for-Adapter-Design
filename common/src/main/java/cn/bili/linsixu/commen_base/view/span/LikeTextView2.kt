package cn.bili.linsixu.commen_base.view.span

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.ColorRes
import android.text.Layout
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView
import cn.bili.linsixu.commen_base.R
import cn.bili.linsixu.commen_base.utils.ImageHelper
import java.lang.StringBuilder

/**
 * Created by Magic
 * on 2019-07-31.
 * email: linsixu@bilibili.com
 */
class LikeTextView2 : TextView {
    private val mutableList = arrayListOf<LikeBean.LikeInfoBean.LikeUsersBean>()
    private val mLinePaint = Paint()
    private var mListener: ISpanItemClickListener? = null
    private var isInitDraw = true
    private val mDecorationHeight = ImageHelper.dp2px(15f, context)
    private val mIconSize = ImageHelper.dp2px(14f, context)
    private var mPaddingOfDrawable = ImageHelper.dp2px(10f, context)
    private val mParams = TextViewParams()
    private var mTips = ""

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
            val array = context.obtainStyledAttributes(attrs, R.styleable.LikeTextView)
            for (i in 0 until array.length()) {
                val index = array.getIndex(i)
                when (index) {
                    R.styleable.LikeTextView_contentTextColor1 -> mParams.mNameTextColor = array.getResourceId(index, mParams.mNameTextColor)
                    R.styleable.LikeTextView_nameTextNightColor1 -> mParams.mNameTextNightColor = array.getResourceId(index, mParams.mNameTextNightColor)
                    R.styleable.LikeTextView_contentTextColor1 -> mParams.mContentTextColor = array.getResourceId(index, mParams.mContentTextColor)
                    R.styleable.LikeTextView_contentTextNightColor1 -> mParams.mContentTextNightColor = array.getResourceId(index, mParams.mContentTextNightColor)
                }
            }
            array.recycle()
        }
        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = resources.getColor(android.R.color.transparent)
        initPaint()
    }

    fun setTextContent(datas: LikeBean.LikeInfoBean) {
        if (mutableList.size != 0) {
            mutableList.clear()
        }
        mTips = datas.display_text
        mutableList.addAll(datas.like_users)
        isInitDraw = true
    }

    fun buildContent(): SpannableStringBuilder {
        var totalWidth = 0f
        setIconImage()
        var actualViewWidth = width - paddingLeft - paddingRight - (mIconSize + mPaddingOfDrawable)
        var result = SpannableStringBuilder()
        var start = 0
        val ellipWidth = paint.measureText(ELLIP_TEXT)
        val dashWidth = paint.measureText(BETWEEN_DASH)
        isInitDraw = false
        if (isContentToLong() && hasMoreSpace()) {
            //如果超长并且扣除name后，剩余空间能装得下三点，三点属于标题
            for (i in 0 until mutableList.size) {
                if (i > 2) break
                if (i == 1) {
                    result.append(BETWEEN_DASH)
                    totalWidth += dashWidth
                }
                val itemWidth = Layout.getDesiredWidth(mutableList[i].uname, paint)
                if (itemWidth <= actualViewWidth - totalWidth) {
                    result.append(mutableList[i].uname)
                    val spanClick = object : LikeClickableSpan<LikeBean.LikeInfoBean.LikeUsersBean>(mutableList[i]) {
                        override fun onDataClick(t: LikeBean.LikeInfoBean.LikeUsersBean) {
                            mListener?.itemSpanClick(t)
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            ds.color = Color.parseColor("#ff3300")
                            ds.bgColor = 0
                            ds.isUnderlineText = false
                        }
                    }
                    result.setSpan(spanClick, start, result.length, 0)
                } else {
                    break
                }
                start += mutableList[i].uname.length
                totalWidth = Layout.getDesiredWidth(result, paint)
            }
            result.append(getFinallyTis(actualViewWidth - Layout.getDesiredWidth(result, paint) - ellipWidth))
        } else if (isContentToLong() && !hasMoreSpace()) {
            //如果超长并且扣除name后，剩余空间装不下三点，三点属于name
            for (i in 0 until mutableList.size) {
                if (i > 2) break
                if (i == 1) {
                    result.append(BETWEEN_DASH)
                    totalWidth += dashWidth
                }
                val itemWidth = Layout.getDesiredWidth(mutableList[i].uname, paint)
                if (itemWidth <= actualViewWidth - totalWidth - ellipWidth) {
                    //放的下文字，直接放下
                    result.append(mutableList[i].uname)
                    val spanClick = object : LikeClickableSpan<LikeBean.LikeInfoBean.LikeUsersBean>(mutableList[i]) {
                        override fun onDataClick(t: LikeBean.LikeInfoBean.LikeUsersBean) {
                            mListener?.itemSpanClick(t)
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            ds.color = Color.parseColor("#ff3300")
                            ds.bgColor = 0
                            ds.isUnderlineText = false
                        }
                    }
                    result.setSpan(spanClick, start, result.length, 0)
                    start += mutableList[i].uname.length
                } else {
                    //放不下这个文字了，需要把这个文字+...来显示
                    var textEnd = mutableList[i].uname.length
                    val textStart = 0
                    while (textEnd > textStart && Layout.getDesiredWidth(mutableList[i].uname, textStart, textEnd, paint) > actualViewWidth - totalWidth - ellipWidth) {
                        textEnd--
                    }
                    val finallyText = if (textEnd <= 0) {
                        ELLIP_TEXT
                    } else if (textEnd == mutableList[i].uname.length) {
                        mutableList[i].uname
                    } else {
                        mutableList[i].uname.substring(textStart, textEnd) + ELLIP_TEXT
                    }
                    result.append(finallyText)
                    val spanClick = object : LikeClickableSpan<LikeBean.LikeInfoBean.LikeUsersBean>(mutableList[i]) {
                        override fun onDataClick(t: LikeBean.LikeInfoBean.LikeUsersBean) {
                            mListener?.itemSpanClick(t)
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            ds.color = Color.parseColor("#ff3300")
                            ds.bgColor = 0
                            ds.isUnderlineText = false
                        }
                    }
                    result.setSpan(spanClick, start, result.length, 0)
                    start += finallyText.length
                    break
                }
                totalWidth = Layout.getDesiredWidth(result, paint)
            }
        } else {
            for (i in 0 until mutableList.size) {
                if (i > 2) break
                if (i > 0 && Layout.getDesiredWidth(result, paint) < actualViewWidth - totalWidth) {
                    result.append(BETWEEN_DASH)
                }
                val itemWidth = Layout.getDesiredWidth(mutableList[i].uname, paint)
                if (itemWidth <= actualViewWidth - totalWidth) {
                    result.append(mutableList[i].uname)
                    val spanClick = object : LikeClickableSpan<LikeBean.LikeInfoBean.LikeUsersBean>(mutableList[i]) {
                        override fun onDataClick(t: LikeBean.LikeInfoBean.LikeUsersBean) {
                            mListener?.itemSpanClick(t)
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            ds.color = Color.parseColor("#ff3300")
                            ds.bgColor = 0
                            ds.isUnderlineText = false
                        }
                    }
                    result.setSpan(spanClick, start, result.length, 0)
                } else {
                    break
                }
                start += mutableList[i].uname.length
                totalWidth = Layout.getDesiredWidth(result, paint)
            }
            result.append(mTips)
        }
        return result
    }

    fun isContentToLong(): Boolean {
        val result = StringBuilder()
        for (i in 0 until mutableList.size) {
            if (i >= 2) break
            result.append(mutableList[i].uname)
            if (i == 0) result.append(BETWEEN_DASH)
        }
        result.append(mTips)
        return Layout.getDesiredWidth(result, paint) > (width - mIconSize - mPaddingOfDrawable) - paddingLeft - paddingRight
    }

    private fun hasMoreSpace(): Boolean {
        val result = StringBuilder()
        for (i in 0 until mutableList.size) {
            if (i >= 2) break
            result.append(mutableList[i].uname)
            if (i == 0) result.append(BETWEEN_DASH)
        }
        return (width - mIconSize - mPaddingOfDrawable) - paddingLeft - paddingRight - Layout.getDesiredWidth(result, paint) > paint.measureText(ELLIP_TEXT)
    }

    private fun getFinallyTis(remainSpace: Float): String {
        var end = mTips.length
        val start = 0
        while (end > start && Layout.getDesiredWidth(mTips, start, end, paint) > remainSpace) {
            end--
        }
        if (end <= 0) {
            return ELLIP_TEXT
        } else if (end == mTips.length) {
            return mTips + ELLIP_TEXT
        } else {
            return mTips.substring(start, end) + ELLIP_TEXT
        }
    }

    fun setIconImage() {
        if (compoundDrawables.isEmpty()) {
            val drawable = context.resources.getDrawable(R.drawable.icon_good)
            drawable.setBounds(0, 0, mIconSize, mIconSize)
            setCompoundDrawables(drawable, null, null, null)
            compoundDrawablePadding = mPaddingOfDrawable
        } else {
            mPaddingOfDrawable = compoundDrawablePadding
            for (drawable in compoundDrawables) {
                drawable?.setBounds(0, 0, mIconSize, mIconSize)
            }
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3])
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.x >= 0 && event.x <= paddingLeft + mIconSize + mPaddingOfDrawable) {
            return true
        }
        return super.dispatchTouchEvent(event)
    }

    private fun initPaint() {
        mLinePaint.isAntiAlias = true
        mLinePaint.strokeWidth = ImageHelper.dp2px(2f, this.context).toFloat()
//        mLinePaint.strokeCap = Paint.Cap.ROUND
        mLinePaint.color = context.resources.getColor(R.color.dynamic_decoration_color)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val test = ImageHelper.dp2px(2f, this.context).toFloat()
        val mPadding = height - mDecorationHeight - test
        canvas?.drawLine(test / 2, mPadding.toFloat() / 2, test / 2, mDecorationHeight.toFloat() - test, mLinePaint)
        if (isInitDraw) text = buildContent()
    }

    interface ISpanItemClickListener {
        fun itemSpanClick(data: LikeBean.LikeInfoBean.LikeUsersBean)
    }

    fun setISpanItemClickListener(listener: ISpanItemClickListener) {
        this.mListener = listener
    }

    data class TextViewParams(
        @ColorRes
        var mNameTextColor: Int = -1,
        @ColorRes
        var mNameTextNightColor: Int = -1,
        @ColorRes
        var mContentTextColor: Int = -1,
        @ColorRes
        var mContentTextNightColor: Int = -1
    )
}