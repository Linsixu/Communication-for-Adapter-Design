package cn.bili.linsixu.commen_base.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.text.Layout
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import android.widget.TextView
import cn.bili.linsixu.commen_base.R

/**
 * Created by Magic
 * on 2019-05-28.
 * email: linsixu@bilibili.com
 */
class ExpandTextView : TextView {
    val defaultMaxLines = 2
    var iconWidth = 100
    var iconHeight = 0
    var iconId = 0
    var isStale = true
    var spaceNumber = 1
    private var mExpandParams = ExpandParams()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun init(context: Context, attrs: AttributeSet) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView)
        try {

        } finally {
            array.recycle()
        }
    }

    private fun setStyle(array: TypedArray) {
        val count = array.indexCount
        for (i in 0 until count) {
            val index = array.getIndex(i)
            when (index) {
                R.styleable.ExpandTextView_icon_width -> mExpandParams.iconWidth = array.getDimensionPixelSize(index, mExpandParams.iconWidth)
                R.styleable.ExpandTextView_icon_height -> mExpandParams.iconHeight = array.getDimensionPixelSize(index, mExpandParams.iconHeight)
                R.styleable.ExpandTextView_space_number -> mExpandParams.spaceNumber = array.getInt(index, mExpandParams.spaceNumber)
                R.styleable.ExpandTextView_icon_background -> mExpandParams.iconBackground = array.getDrawable(index)
                R.styleable.ExpandTextView_max_rows -> mExpandParams.maxRows = array.getInt(index, mExpandParams.maxRows)
            }
        }
    }

    init {
        val CONSTANT_STRING_EXPAND = context.getString(R.string.following_text_expand)
    }

    companion object {
        val ELLIPSIS_NORMAL = charArrayOf('\u2026') // this is "..."
        val ELLIPSIS_STRING = String(ELLIPSIS_NORMAL)

        //space
        val SPACE_UNICODE_16 = charArrayOf('\u0020')//this is " "
        val SPACE_STRING = String(SPACE_UNICODE_16)

        val CONSTANT_STRING_EXPAND = "..."
    }

    private fun calculateEllipsize(workingText: CharSequence): CharSequence {
        var workingText = workingText
        val maxLines = defaultMaxLines
        val layout = layout
        if (maxLines != -1 && layout.lineCount >= maxLines) {
            val start = layout.getLineStart(maxLines - 1)
            var end = layout.getLineEnd(maxLines - 1)
            val paint = paint
            //先量出 EXPAND 宽度
            val expandString = getExpandString() + SPACE_STRING
            //为啥不用 paint.measureText了呢 因为oppo手机 会有bug 导致测量不正确  而下面的测量却没有错
            var expandWidth = Layout.getDesiredWidth(expandString, 0, expandString.length, paint) + iconWidth

            val space1 = Layout.getDesiredWidth(SPACE_STRING, 0, SPACE_STRING.length, paint)
            expandWidth += space1
            //展示不下才走进来，先减减，看剩余空白够不够展示 EXPAND，不够再减，减到行首为止
            while (--end >= start && layout.width - Layout.getDesiredWidth(workingText, start, end, paint) < expandWidth) {
            }
            //防止 数组越界
            if (end < 0) {
                end = 0
            }
            workingText = SpannableStringBuilder(workingText.subSequence(0, end)).append(getExpandString()).append(SPACE_STRING)
            val imageSpan1 = MyIm(context, R.drawable.warning, (layout.width - Layout.getDesiredWidth(workingText, start, end, paint) - expandWidth + space1).toInt(), layout.height)
            imageSpan1.setmLines(layout.lineCount)
            imageSpan1.setDefaultMaxLine(defaultMaxLines)
            workingText.setSpan(imageSpan1, workingText.length - 1, workingText.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        }
        return workingText
    }

    fun setChangeText(text: CharSequence) {
        val result = calculateEllipsize(text)
        super.setText(result)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isStale) {
            setChangeText(this.text)
            isStale = false
        }
    }

    fun getExpandString(): String {
        return CONSTANT_STRING_EXPAND
    }
}