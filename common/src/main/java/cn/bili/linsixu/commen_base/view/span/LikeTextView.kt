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
import cn.bili.linsixu.commen_base.utils.ImageHelper
import cn.bili.linsixu.commen_base.R
import cn.bili.linsixu.commen_base.utils.ImageHelper.dp2px
import java.lang.StringBuilder

/**
 * Created by Magic
 * on 2019-07-29.
 * 可以不用就不用，好傻逼的逻辑
 * email: linsixu@bilibili.com
 */
class LikeTextView : TextView {
    private val mutableList = arrayListOf<LikeBean.LikeInfoBean.LikeUsersBean>()
    private val mLinePaint = Paint()
    private var mListener: ISpanItemClickListener? = null
    private var isInitDraw = true
    private val mDecorationHeight = dp2px(15f, context)
    private val mIconSize = dp2px(14f, context)
    private var mPaddingOfDrawable = dp2px(10f, context)
    private val mParams = TextViewParams()
    private var isBelongName = false
    private var isNametoLong = false
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
        isInitDraw = false
        isBelongName = paint.measureText(mTips) < paint.measureText(ELLIP_TEXT) && isContentToLong()
        if (isContentToLong()) {
            //如果知道超长了，预留三点位置
            actualViewWidth -= paint.measureText(ELLIP_TEXT).toInt()
        }
        for (i in 0 until mutableList.size) {
            if (i >= 2) break
            if (Layout.getDesiredWidth(mutableList[i].uname, paint) > actualViewWidth - totalWidth) {
                isNametoLong = true
                var textStart = 0
                var textEnd = mutableList[i].uname.length//exclude
                while (textEnd > textStart && Layout.getDesiredWidth(mutableList[i].uname, textStart, textEnd, paint) > actualViewWidth - totalWidth - ellipWidth) {
                    //include，到textEnd的时候是满足条件的，所以下面要+1，因为substring是[x1,x2)
                    textEnd--
                }
                result.append(mutableList[i].uname.substring(textStart, textEnd + 1) + ELLIP_TEXT)
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
                result.setSpan(spanClick, start, start + textEnd + 1 + ELLIP_TEXT.length, 0)
                break
            } else {
                //只控制有两个名称展示
                if (isBelongName && (i == 1 || i == mutableList.size)) {
                    //加上tips超出宽度，而且tips宽度不够，要删除到name
                    isNametoLong = true
                    var textStart = 0
                    var textEnd = mutableList[i].uname.length//exclude
                    while (textEnd > textStart && Layout.getDesiredWidth(mutableList[i].uname, textStart, textEnd, paint) > actualViewWidth - totalWidth - ellipWidth) {
                        textEnd--
                    }
                    result.append(mutableList[i].uname.substring(textStart, textEnd + 1) + ELLIP_TEXT)
                    val spanClick = object : LikeClickableSpan<LikeBean.LikeInfoBean.LikeUsersBean>(mutableList[i]) {
                        override fun onDataClick(t: LikeBean.LikeInfoBean.LikeUsersBean) {
                            mListener?.itemSpanClick(t)
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            ds.color = Color.parseColor("#ff3300")
                            ds.isUnderlineText = false
                        }
                    }
                    result.setSpan(spanClick, start, start + textEnd + 1 + ELLIP_TEXT.length, 0)
                    break
                } else {
                    //name正常显示
                    result.append(mutableList[i].uname)
                    if (i == 0 && mutableList.size > 1) result.append(BETWEEN_DASH)
                    val spanClick = object : LikeClickableSpan<LikeBean.LikeInfoBean.LikeUsersBean>(mutableList[i]) {
                        override fun onDataClick(t: LikeBean.LikeInfoBean.LikeUsersBean) {
                            mListener?.itemSpanClick(t)
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            ds.color = Color.parseColor("#ff3300")
                            ds.isUnderlineText = false
                        }
                    }
                    result.setSpan(spanClick, start, result.length, 0)
                    start = result.length
                    totalWidth = Layout.getDesiredWidth(result, paint)
                }
            }
        }
        //如果name名字没有超长，才需要添加提示
        if (!isContentToLong()) {
            result.append(mTips)
        } else if (isContentToLong() && !isNametoLong) {
            //加了小尾巴超长
            //因为前面确定了tip比三点内容长，这里就不会出现删除到name那里了
            val textStart = 0
            var textEnd = mTips.length
            while (textEnd > textStart && Layout.getDesiredWidth(mTips, 0, textEnd, paint) > actualViewWidth - Layout.getDesiredWidth(result, 0, result.length, paint)) {
                textEnd--
            }
            if (textEnd <= textStart) {
                result.append(ELLIP_TEXT)
            } else if (textEnd == mTips.length) {
                result.append(mTips)
            } else {
                result.append(mTips.substring(0, textEnd) + ELLIP_TEXT)
            }
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
        mLinePaint.color = context.resources.getColor(R.color.dynamic_decoration_color)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val mPadding = height - mDecorationHeight
        canvas?.drawLine(0f, mPadding.toFloat() / 2, 0f, mDecorationHeight.toFloat(), mLinePaint)
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