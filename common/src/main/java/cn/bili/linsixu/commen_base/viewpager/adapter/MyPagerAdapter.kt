package cn.bili.linsixu.commen_base.viewpager.adapter

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Created by Magic
 * on 2019-07-19.
 * email: linsixu@bilibili.com
 */
class MyPagerAdapter(val images: ArrayList<ImageView>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        container?.addView(images[position])
        return images[position]
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(images[position])
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return images.size
    }
}