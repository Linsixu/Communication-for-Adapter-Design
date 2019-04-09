package cn.bili.linsixu.commen_base.feed.card

import android.support.annotation.UiThread
import android.support.v7.widget.RecyclerView
import android.view.View
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
abstract class BaseCardViewHolder<T : FeedItem>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    lateinit var data: T
    var dataNullable: Boolean = false
    private var mInterActionHandler: InterActionHandler<InterAction>? = null

    /**
     *
     * @param data
     * @return Bind success or no，only when data cannot be null may return false.
     */
    private var genericityType: Type? = null

    init {
        genericityType = (javaClass.genericSuperclass as? ParameterizedType)?.actualTypeArguments?.getOrNull(0)
    }

    /**
     *
     * @param data
     * @return Bind success or no，only when data cannot be null may return false.
     */
    @Suppress("UNCHECKED_CAST")
    open fun bindData(data: Any?): Boolean {
        if (data == null && !dataNullable) return true
        if (data?.javaClass == genericityType) {
            this.data = data as T
        } else {
            throw IllegalArgumentException("Please check your json parser.")
        }
        return true
    }

    open fun onViewRecycled() {}

    open fun setInterActionHandler(interActionHandler: InterActionHandler<InterAction>?) {
        mInterActionHandler = interActionHandler
    }

    @UiThread
    open fun sendAction(action: InterAction) {
        mInterActionHandler?.onAction(action)
    }
}