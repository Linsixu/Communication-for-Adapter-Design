package cn.bili.linsixu.commen_base.feed.card

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
abstract class Card<VH : BaseCardViewHolder<T>, T : FeedItem> {
    var holder: VH? = null
    lateinit var data: T

    abstract val viewType: Int

    open fun bindData(data: T) {
        this.data = data
    }

    open fun onBindViewHolder(holder: VH, position: Int) {
        if (holder.itemView == null) {
            return
        }
        this.holder = holder
        holder.bindData(data)
    }
}