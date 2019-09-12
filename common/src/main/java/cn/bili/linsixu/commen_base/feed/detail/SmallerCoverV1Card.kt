package cn.bili.linsixu.commen_base.feed.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import cn.bili.linsixu.commen_base.R
import cn.bili.linsixu.commen_base.feed.base.BasePegasusCard
import cn.bili.linsixu.commen_base.feed.base.BasePegasusHolder
import cn.bili.linsixu.commen_base.feed.base.CardType
import cn.bili.linsixu.commen_base.feed.item.SmallerCoverItem
import cn.bili.linsixu.commen_base.utils.MyLog
import cn.bili.linsixu.commen_base.view.ExpandView
import cn.bili.linsixu.commen_base.view.span.LikeBean
import cn.bili.linsixu.commen_base.view.span.LikeTextView
import cn.bili.linsixu.commen_base.view.span.LikeTextView2

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
        val testView = itemView.findViewById<LikeTextView>(R.id.testView)
        val testView2 = itemView.findViewById<LikeTextView2>(R.id.testView2)

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

            val list = mutableListOf<String>("s大火烧多", "黄金大数据", "dsaads", "asdasdjkda", "sad22333", "dsau8erwuerw", "1223")
            val f = LikeBean.LikeInfoBean()

            val listTest = mutableListOf<LikeBean.LikeInfoBean.LikeUsersBean>()
            val test = LikeBean.LikeInfoBean.LikeUsersBean()
            test.uname = "噶世界各地回家d弟噶世界各地回家"
//            test.uname = "噶世界各地回家dsakjsad哦我i简单完全aff我去和我求婚的洒"

            val f2 = LikeBean.LikeInfoBean.LikeUsersBean()
            f2.uname = "23112asdasd我弟话噶世界salads"

            listTest.add(test)
            listTest.add(f2)
            f.like_users = listTest
            f.display_text = "abc赞了ab"



            testView.setTextContent(f)
            testView.setISpanItemClickListener(object : LikeTextView.ISpanItemClickListener {
                override fun itemSpanClick(data: LikeBean.LikeInfoBean.LikeUsersBean) {
//                    Toast.makeText(itemView.context, data.uname, Toast.LENGTH_SHORT).show()
//                    MyLog.i("magic", "view=$data")
                }
            })

            testView2.setTextContent(f)
            testView2.setISpanItemClickListener(object : LikeTextView2.ISpanItemClickListener {
                override fun itemSpanClick(data: LikeBean.LikeInfoBean.LikeUsersBean) {
                    Toast.makeText(itemView.context, data.uname, Toast.LENGTH_SHORT).show()
                    MyLog.i("magic", "view=$data")
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