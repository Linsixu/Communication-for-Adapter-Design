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
import cn.bili.linsixu.commen_base.utils.MyLog
import cn.bili.linsixu.commen_base.view.ExpandView

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
        val mTextView: ExpandView = itemView.findViewById(R.id.text_expand)

        override fun bind() {
            mUserName.text = data.name
            mAdress.text = data.address
            mBook.text = data.book
            mDesc.text = data.desc
            mNumber.text = data.number

            mTextView.setText("我我哦哦我家阿手机客户端开会啊哈就开始大家看哈是客户端看我哦哦我家阿手机客户端开会啊哈就开始大家看哈是客我哦哦我家阿手机客户端开会啊哈就开始大家看哈是客")
            mTextView.setOnIconClickListener(object : ExpandView.OnIconClickListener {
                override fun onClick() {
                    MyLog.i("magic", "icon click")
                }
            })
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