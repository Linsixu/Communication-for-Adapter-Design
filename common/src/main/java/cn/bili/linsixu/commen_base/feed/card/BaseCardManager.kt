package cn.bili.linsixu.commen_base.feed.card

import android.view.ViewGroup

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
abstract class BaseCardManager<T : Card<*, *>, V : FeedItem, in VH : BaseCardViewHolder<V>> {
    var mCardList: MutableList<T> = ArrayList()

    val itemCount: Int
        get() = mCardList.size

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCardViewHolder<*>?

    open fun onBindViewHolder(holder: VH, position: Int) {
        val card = mCardList[position]
        (card as? Card<VH, V>)?.onBindViewHolder(holder, position)
    }

    open fun onViewRecycled(holder: VH) {
        holder.onViewRecycled()
    }

    open fun getItemViewType(position: Int): Int {
        return mCardList[position].viewType
    }

    abstract fun createCard(item: V): T?

    open fun addCard(item: V) {
        val card = createCard(item) ?: return
        mCardList.add(card)
    }

    open fun addCard(card: T?) {
        if (card == null) return
        mCardList.add(card)
    }

    open fun setCardList(itemList: List<V>) {
        mCardList.clear()
        for (item in itemList) {
            addCard(item)
        }
    }

    open fun addCardList(itemList: List<V>) {
        for (item in itemList) {
            addCard(item)
        }
    }

    open fun getCard(position: Int): T? {
        return try {
            mCardList[position]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    open fun replaceCard(position: Int, card: T?) {
        if (card != null) {
            mCardList[position] = card
        }
    }

    open fun indexOfCard(card: T): Int {
        return mCardList.indexOf(card)
    }

    open fun removeCard(card: T): Int {
        val position = mCardList.indexOf(card)
        if (position >= 0) {
            removeCard(position)
        }
        return position
    }

    open fun removeCard(position: Int) {
        if (position >= 0) {
            mCardList.removeAt(position)
        }
    }

    open fun clearCard() {
        mCardList.clear()
    }
}