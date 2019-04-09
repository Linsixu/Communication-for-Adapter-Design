package cn.bili.linsixu.commen_base.feed.base

import android.content.Context
import android.net.Uri
import android.widget.Toast

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
class CardClickProcessor(val createType: Int) {
    //点击事件统一调用
    @JvmOverloads
    fun onCardClick(context: Context, content: String) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }
}