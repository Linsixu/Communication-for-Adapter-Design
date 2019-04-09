package cn.bili.linsixu.commen_base.feed.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import cn.bili.linsixu.commen_base.feed.card.BaseCardManager
import cn.bili.linsixu.commen_base.feed.card.BaseCardViewHolder

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
open class BaseCardAdapter<T : BaseCardViewHolder<*>>(
    private var mCardManager: BaseCardManager<*, *, T>
) : RecyclerView.Adapter<T>() {
    override fun getItemViewType(position: Int): Int {
        return mCardManager.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return mCardManager.itemCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return mCardManager.onCreateViewHolder(parent, viewType) as T
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        mCardManager.onBindViewHolder(holder, position)
    }

    override fun onViewRecycled(holder: T) {
        super.onViewRecycled(holder)
        mCardManager.onViewRecycled(holder)
    }
}