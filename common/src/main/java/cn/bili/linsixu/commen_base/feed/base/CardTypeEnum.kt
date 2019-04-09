package cn.bili.linsixu.commen_base.feed.base

import cn.bili.linsixu.commen_base.feed.BaseIndexItem
import cn.bili.linsixu.commen_base.feed.item.LargeCoverV1Item
import cn.bili.linsixu.commen_base.feed.item.SmallerCoverItem

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
enum class CardTypeEnum(value: Int, impl: Class<out BaseIndexItem>)  {
    LARGE_COVER_V1(CardType.LARGE_COVER_V1, LargeCoverV1Item::class.java),
    LARGE_COVER_V2(CardType.SMALL_COVER_V1, SmallerCoverItem::class.java);

    var value: Int
        internal set
    var impl: Class<out BaseIndexItem>
        internal set

    init {
        this.value = value
        this.impl = impl
    }
}