package cn.bili.linsixu.commen_base.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.TextUtils
import android.text.style.ImageSpan
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import cn.bili.linsixu.commen_base.R

/**
 * Created by Magic
 * on 2019-05-27.
 * email: linsixu@bilibili.com
 */
class ExpandLinearLayout : LinearLayout {
    var mTextView: ExpandTextView? = null

    constructor(context: Context?) : super(context) {
        init(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        orientation = HORIZONTAL

        mTextView = ExpandTextView(context)

        val spanString: SpannableStringBuilder = SpannableStringBuilder()
        spanString.append("未确定内容真实性，请谨慎识别我肯定就会升级换代卡还是进口的话即哈萨克觉得很愧疚啊黑色的阿设计的肯定就会升级换代卡还是进口的话即哈萨克觉得很愧疚啊黑色的阿设计的肯定就会升级换代卡还是进口的话即哈萨克觉得很愧疚啊黑色的阿设计的")
        val imageSpan1 = MyIm(context, R.drawable.warning)
        val d = imageSpan1.drawable
        val width = d.bounds.right

        spanString.setSpan(imageSpan1, spanString.length - 1, spanString.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        mTextView?.let {
            it.includeFontPadding = false
            it.maxLines = 2
//            it.ellipsize = TextUtils.TruncateAt.END
            it.text = spanString
            it.textSize = 20f
            it.iconWidth = width
        }
        val p = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        p.setMargins(10, 0, 10, 0)
        addView(mTextView, p)
    }
}