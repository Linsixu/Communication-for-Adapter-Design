package cn.bili.linsixu.commen_base.feed.base

import android.support.v4.app.Fragment
import cn.bili.linsixu.commen_base.feed.BaseIndexItem
import cn.bili.linsixu.commen_base.feed.card.Card
import cn.bili.linsixu.commen_base.feed.card.InterAction
import cn.bili.linsixu.commen_base.feed.card.InterActionHandler

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
abstract class BasePegasusCard<CVH : BasePegasusHolder<T>, T : BaseIndexItem> : Card<CVH, T>() {

    var fragment: Fragment? = null
    var clickProcessor: CardClickProcessor? = null

    fun bindFragment(fragment: Fragment, clickProcessor: CardClickProcessor?) {
        this.fragment = fragment
        this.clickProcessor = clickProcessor
    }

    override fun onBindViewHolder(holder: CVH, position: Int) {
        holder.fragment = fragment
        if (fragment is InterActionHandler<*>) {
            holder.setInterActionHandler(fragment as? InterActionHandler<InterAction>)
        }
        holder.clickProcessor = clickProcessor
        super.onBindViewHolder(holder, position)
    }
}