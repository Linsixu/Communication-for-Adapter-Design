package cn.bili.linsixu.commen_base.viewpager.ui

import android.graphics.Color
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import cn.bili.linsixu.commen_base.R
import cn.bili.linsixu.commen_base.base.BaseActivity
import cn.bili.linsixu.commen_base.viewpager.adapter.MyPagerAdapter
import cn.bili.linsixu.commen_base.viewpager.animation.PagerTransformer
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * Created by Magic
 * on 2019-07-19.
 * email: linsixu@bilibili.com
 */
@Route(path = "/common/PagerActivity")
class PagerActivity : AppCompatActivity() {
    var mList: ArrayList<ImageView> = ArrayList()
    lateinit var mViewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager_animation)
        mViewPager = findViewById(R.id.view_pager)

        for (i in 0 until 5) {
            val imageView = ImageView(this)
            imageView.setBackgroundColor(Color.parseColor("#ccee$i$i"))
            mList.add(imageView)
        }
        val adapter = MyPagerAdapter(mList)
        mViewPager.adapter = adapter
        mViewPager.setPageTransformer(true, PagerTransformer())
    }
}