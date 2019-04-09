package cn.bili.linsixu.commen_base.feed.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.bili.linsixu.commen_base.R
import cn.bili.linsixu.commen_base.feed.base.BasePegasusCard
import cn.bili.linsixu.commen_base.feed.base.BasePegasusHolder
import cn.bili.linsixu.commen_base.feed.base.CardType
import cn.bili.linsixu.commen_base.feed.item.SmallerCoverItem

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
class SmallerCoverV1Card : BasePegasusCard<SmallerCoverV1Card.SmallCoverV1Holder, SmallerCoverItem>() {
    override val viewType: Int
        get() = CardType.SMALL_COVER_V1

    class SmallCoverV1Holder(itemView: View) : BasePegasusHolder<SmallerCoverItem>(itemView) {
        val mBook = itemView.findViewById<TextView>(R.id.txt_book)
        val mDesc = itemView.findViewById<TextView>(R.id.txt_desc)
        val mUserName = itemView.findViewById<TextView>(R.id.txt_userName)
        val mAdress = itemView.findViewById<TextView>(R.id.txt_address)
        val mNumber = itemView.findViewById<TextView>(R.id.txt_number)

        override fun bind() {
            mUserName.text = data.name
            mAdress.text = data.address
            mBook.text = data.book
            mDesc.text = data.desc
            mNumber.text = data.number
        }
    }

    companion object {

        fun createViewHolder(parent: ViewGroup): SmallCoverV1Holder {
            return SmallCoverV1Holder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_smaller_cover_v1_view_item,
                    parent,
                    false
                )
            )
        }
    }
}