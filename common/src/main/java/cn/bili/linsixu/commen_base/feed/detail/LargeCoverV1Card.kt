package cn.bili.linsixu.commen_base.feed.detail

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.transition.Fade
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import cn.bili.linsixu.commen_base.R
import cn.bili.linsixu.commen_base.activity.FeedActivity
import cn.bili.linsixu.commen_base.activity.ShareElementActivity
import cn.bili.linsixu.commen_base.element.ICardItemClickListener
import cn.bili.linsixu.commen_base.element.IShareElementCard
import cn.bili.linsixu.commen_base.feed.base.BasePegasusCard
import cn.bili.linsixu.commen_base.feed.base.BasePegasusHolder
import cn.bili.linsixu.commen_base.feed.base.CardType
import cn.bili.linsixu.commen_base.feed.item.LargeCoverV1Item
import cn.bili.linsixu.commen_base.utils.MyLog
import com.bumptech.glide.Glide

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@TargetApi(Build.VERSION_CODES.KITKAT)
class LargeCoverV1Card : BasePegasusCard<LargeCoverV1Card.LargeCoverV1Holder, LargeCoverV1Item>() {
    override val viewType: Int
        get() = CardType.LARGE_COVER_V1

    class LargeCoverV1Holder(itemView: View) : BasePegasusHolder<LargeCoverV1Item>(itemView) {

        private val mFeed = itemView.findViewById<TextView>(R.id.txt_feed)
        private val mCover = itemView.findViewById<ImageView>(R.id.image_cover)
        private val mName = itemView.findViewById<TextView>(R.id.txt_name)
        private val mNumber = itemView.findViewById<TextView>(R.id.txt_number)
        private val layout = itemView.findViewById<LinearLayout>(R.id.layout)
        private var mInitLeft = 0
        private var mInitTop = 0

        private var exit: Transition? = null

        override fun bind() {
            val activity = itemView.context as FeedActivity
            val data1 = data as? LargeCoverV1Item
//            exit = activity.window.sharedElementExitTransition
            exit = activity.window.sharedElementReenterTransition
            mFeed.text = data1?.feed
            Glide.with(activity).load(data1?.cover).into(mCover)
            mName.text = data1?.name
            mNumber.text = data1?.number

            val rect = Rect()
            itemView.getDrawingRect(rect)

            itemView.setOnClickListener(View.OnClickListener {
                val intent = Intent(activity, ShareElementActivity::class.java)
                val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                    Pair(itemView.findViewById(R.id.layout), FeedActivity.LAYOUR_FLAG),
                    Pair(itemView.findViewById(R.id.txt_feed), FeedActivity.TXT_FLAG))
                openDynamic(activity, intent = intent, activityOptions = activityOptions)
            })
        }

        fun openDynamic(activity: Activity, intent: Intent, activityOptions: ActivityOptionsCompat) {
            val rect = Rect()
            val a = intArrayOf(0, 0)
//            var set = AnimationSet(true)
            layout.getLocationOnScreen(a)
            val width = layout.width
            val height = layout.height
            val l = layout.left
            val top = layout.top
            mInitLeft = l
            mInitTop = top
            val animator = ValueAnimator.ofInt(0, 400)
            animator.duration = 4000
            animator.addUpdateListener {
                val curValue = it.animatedValue as? Int
                layout.layout(l, top - curValue!!, l + width, top + height - curValue)
                if (curValue == 400) ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
            }
            animator.start()

            val scaleAnimator = ValueAnimator.ofFloat(1f, 2f)
            var scaleX = 1f
            var scaleY = 1f
            val scale = ScaleAnimation(scaleX, scaleX + 0.05f, scaleY, scaleY + 0.05f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            scale.duration = 4000
            mFeed.startAnimation(scale)
            layout.startAnimation(scale)

            val scaleText = ScaleAnimation(scaleX, scaleX + 1f, scaleY, scaleY + 1f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f)

            val alphaAnim = AlphaAnimation(1.0f, 0.0f)
            val animationSet = AnimationSet(true)
            animationSet.addAnimation(scaleText)
            animationSet.addAnimation(alphaAnim)
            animationSet.duration = 4000
            animationSet.fillAfter = true
            mFeed.startAnimation(animationSet)

            val enter = activity.window.sharedElementEnterTransition
            enter.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition?) {
                }

                override fun onTransitionResume(transition: Transition?) {
                }

                override fun onTransitionPause(transition: Transition?) {
                }

                override fun onTransitionCancel(transition: Transition?) {
                }

                override fun onTransitionStart(transition: Transition?) {
                }
            })


            exit?.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition?) {
                    exit?.removeListener(this)
                    MyLog.i("", "onTransitionEnd-${transition!!.getTransitionValues(layout, true).values}")
                }

                override fun onTransitionResume(transition: Transition?) {
                    MyLog.i("", "onTransitionResume-")
                }

                override fun onTransitionPause(transition: Transition?) {
                    MyLog.i("", "onTransitionPause-")
                }

                override fun onTransitionCancel(transition: Transition?) {
                    exit?.removeListener(this)
                    MyLog.i("", "onTransitionCancel-")
                }

                override fun onTransitionStart(transition: Transition?) {
                    closeDynamic()
                    MyLog.i("", "onTransitionStart-${transition!!.getTransitionValues(layout, true).values.size}")
                }
            })
        }

        fun closeDynamic() {

            var scaleX = 2f
            var scaleY = 2f
            val scaleText = ScaleAnimation(scaleX, scaleX - 1f, scaleY, scaleY - 1f, Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f)

            val alphaAnim = AlphaAnimation(0.0f, 1.0f)
            val animationSet = AnimationSet(true)
            animationSet.addAnimation(scaleText)
            animationSet.addAnimation(alphaAnim)
            animationSet.duration = 4000
            animationSet.fillAfter = true
            mFeed.startAnimation(animationSet)
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