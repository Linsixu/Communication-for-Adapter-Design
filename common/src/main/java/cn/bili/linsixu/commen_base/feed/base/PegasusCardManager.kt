package cn.bili.linsixu.commen_base.feed.base

import android.support.v4.app.Fragment
import android.view.ViewGroup
import cn.bili.linsixu.commen_base.feed.BaseIndexItem
import cn.bili.linsixu.commen_base.feed.card.BaseCardManager
import cn.bili.linsixu.commen_base.feed.card.BaseCardViewHolder

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
class PegasusCardManager(
    private val creator: ICardCreator,
    private val mCreateType: Int
) :
    BaseCardManager<BasePegasusCard<*, *>, BaseIndexItem, BasePegasusHolder<BaseIndexItem>>() {

    val cardClickProcessor = CardClickProcessor(mCreateType)

    override fun createCard(item: BaseIndexItem): BasePegasusCard<*, *>? {
        var viewType = item.viewType
        if (viewType == 0) {
            viewType = item.cardType?.hashCode() ?: 0
            item.viewType = viewType
        }
        return creator.onCreateCard(item, viewType) as BasePegasusCard<*, *>
    }

    fun createCard(item: BaseIndexItem, fragment: Fragment): BasePegasusCard<*, *>? {
        val card = createCard(item)
        card?.bindFragment(fragment, cardClickProcessor)
        return card
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCardViewHolder<*> {
        return creator.onCreateViewHolder(parent, viewType, mCreateType)
    }

    fun getEngineName(): String? {
        return creator.getEngineName()
    }

    //运营tab页下的卡片一定是v2
    fun isV1StyleType(cardType: Int): Boolean {
        return when (cardType) {
            CardType.LARGE_COVER_V1,
            CardType.SMALL_COVER_V1 -> true
            else -> {
                false
            }
        }
    }

    fun isNeedDrawLine(cardType: Int): Boolean {
        return when (cardType) {
            CardType.LARGE_COVER_V1,
            CardType.SMALL_COVER_V1 -> true
            else -> {
                false
            }
        }
    }

    fun useFullSpan(cardType: Int): Boolean {
        when (cardType) {
            CardType.LARGE_COVER_V1,
            CardType.SMALL_COVER_V1 -> return true
            else -> {
                return false
            }
        }
    }

    fun avoidEmptyWindow(list: MutableList<BaseIndexItem>) {
        if (list.isEmpty()) {
            return
        }
        val smallCardCountBeforeLastBigCard = getSmallCardCountBeforeLastBigCard(list)

        //如果小卡数量为奇数，去掉末尾的小卡，避免空窗
        if (smallCardCountBeforeLastBigCard % 2 != 0) {
            list.removeAt(list.size - 1)
        }
    }

    private fun getSmallCardCountBeforeLastBigCard(list: List<BaseIndexItem>): Int {
        //从后往前遍历，直到第一个大卡片，得到从后往前小卡的数量
        var smallCardCountBeforeLastBigCard = 0
        for (i in list.indices.reversed()) {
            if (useFullSpan(list[i].viewType)) {
                break
            }
            smallCardCountBeforeLastBigCard++
        }
        return smallCardCountBeforeLastBigCard
    }
}