package cn.bili.linsixu.commen_base.feed.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import cn.bili.linsixu.commen_base.R
import cn.bili.linsixu.commen_base.feed.base.BasePegasusCard
import cn.bili.linsixu.commen_base.feed.base.BasePegasusHolder
import cn.bili.linsixu.commen_base.feed.base.CardType
import cn.bili.linsixu.commen_base.feed.item.LargeCoverV1Item
import com.bumptech.glide.Glide

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
class LargeCoverV1Card: BasePegasusCard<LargeCoverV1Card.LargeCoverV1Holder, LargeCoverV1Item>() {
    override val viewType: Int
        get() = CardType.LARGE_COVER_V1

    class LargeCoverV1Holder(itemView: View) : BasePegasusHolder<LargeCoverV1Item>(itemView){
        private val mFeed = itemView.findViewById<TextView>(R.id.txt_feed)
        private val mCover = itemView.findViewById<ImageView>(R.id.image_cover)
        private val mName = itemView.findViewById<TextView>(R.id.txt_name)
        private val mNumber = itemView.findViewById<TextView>(R.id.txt_number)

        override fun bind() {
            val data1 = data as? LargeCoverV1Item
            mFeed.text = data1?.feed
            Glide.with(itemView.context).load(data1?.cover).into(mCover)
            mName.text = data1?.name
            mNumber.text = data1?.number
        }
    }

    companion object {
        fun createViewHolder(parent: ViewGroup): LargeCoverV1Holder {
            return LargeCoverV1Card.LargeCoverV1Holder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_large_cover_v1_view_item,
                    parent,
                    false
                )
            )
        }
    }
}