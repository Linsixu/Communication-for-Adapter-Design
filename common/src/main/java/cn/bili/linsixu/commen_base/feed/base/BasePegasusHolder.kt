package cn.bili.linsixu.commen_base.feed.base

import android.support.v4.app.Fragment
import android.view.View
import cn.bili.linsixu.commen_base.feed.BaseIndexItem
import cn.bili.linsixu.commen_base.feed.card.BaseCardViewHolder

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
abstract class BasePegasusHolder<T : BaseIndexItem>(itemView: View) : BaseCardViewHolder<T>(itemView) {

    var fragment: Fragment? = null
    var clickProcessor: CardClickProcessor? = null

    override fun bindData(data: Any?): Boolean {
        val success = super.bindData(data)
        if (success) {
            bind()
            return true
        }
        return false
    }

    protected abstract fun bind()
}