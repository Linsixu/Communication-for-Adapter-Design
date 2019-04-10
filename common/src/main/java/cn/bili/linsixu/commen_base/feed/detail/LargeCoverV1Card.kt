package cn.bili.linsixu.commen_base.feed.detail

import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import cn.bili.linsixu.commen_base.R
import cn.bili.linsixu.commen_base.activity.FeedActivity
import cn.bili.linsixu.commen_base.activity.ShareElementActivity
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
class LargeCoverV1Card : BasePegasusCard<LargeCoverV1Card.LargeCoverV1Holder, LargeCoverV1Item>() {
    override val viewType: Int
        get() = CardType.LARGE_COVER_V1

    class LargeCoverV1Holder(itemView: View) : BasePegasusHolder<LargeCoverV1Item>(itemView) {
        private val mFeed = itemView.findViewById<TextView>(R.id.txt_feed)
        private val mCover = itemView.findViewById<ImageView>(R.id.image_cover)
        private val mName = itemView.findViewById<TextView>(R.id.txt_name)
        private val mNumber = itemView.findViewById<TextView>(R.id.txt_number)

        @TargetApi(Build.VERSION_CODES.KITKAT)
        override fun bind() {
            val activity = itemView.context as Activity
            val data1 = data as? LargeCoverV1Item
            mFeed.text = data1?.feed
            Glide.with(activity).load(data1?.cover).into(mCover)
            mName.text = data1?.name
            mNumber.text = data1?.number

            itemView.setOnClickListener(View.OnClickListener {
                val intent = Intent(activity,ShareElementActivity::class.java)
                val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, Pair(itemView.findViewById(R.id.layout), FeedActivity.LAYOUR_FLAG),
                    Pair(itemView.findViewById(R.id.txt_feed), FeedActivity.TXT_FLAG))
                ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
            })
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