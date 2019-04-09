package cn.bili.linsixu.commen_base.feed.base

import android.view.ViewGroup
import cn.bili.linsixu.commen_base.feed.card.BaseCardViewHolder
import cn.bili.linsixu.commen_base.feed.card.Card
import cn.bili.linsixu.commen_base.feed.card.FeedItem

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
interface ICardCreator {
    fun onCreateCard(item: FeedItem, viewType: Int): Card<*, *>

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int, createType: Int): BaseCardViewHolder<*>

    fun getEngineName(): String?
}