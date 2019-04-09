package cn.bili.linsixu.commen_base.feed.base

import android.view.ViewGroup
import cn.bili.linsixu.commen_base.feed.card.BaseCardViewHolder
import cn.bili.linsixu.commen_base.feed.card.Card
import cn.bili.linsixu.commen_base.feed.card.FeedItem
import cn.bili.linsixu.commen_base.feed.detail.LargeCoverV1Card
import cn.bili.linsixu.commen_base.feed.detail.SmallerCoverV1Card

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
class BasicCardCreatorV2(
    private val feedEngineName: String?
) : ICardCreator {
    override fun onCreateCard(item: FeedItem, viewType: Int): Card<*, *> {
        if (viewType == 0) throw IllegalArgumentException("card viewType cannot be 0!")
        val card = when (viewType) {
            CardType.LARGE_COVER_V1 -> LargeCoverV1Card()
            CardType.SMALL_COVER_V1 -> SmallerCoverV1Card()
            else -> throw IllegalArgumentException("card is unknown")
        }
        (card as? Card<BaseCardViewHolder<FeedItem>, FeedItem>)?.bindData(item)
        return card
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, createType: Int): BaseCardViewHolder<*> {
        return when (viewType) {
            CardType.LARGE_COVER_V1 -> LargeCoverV1Card.createViewHolder(parent)
            CardType.SMALL_COVER_V1 -> SmallerCoverV1Card.createViewHolder(parent)
            else -> throw IllegalArgumentException("card is unknown")
        }
    }

    override fun getEngineName(): String? {
        return feedEngineName
    }
}