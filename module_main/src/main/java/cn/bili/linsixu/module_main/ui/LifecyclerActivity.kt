/*
 * bilibili:android studio write this method in 19-1-9 上午10:38
 */

package cn.bili.linsixu.module_main.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import cn.bili.linsixu.commen_base.base.BaseActivity
import cn.bili.linsixu.commen_base.utils.MyLog
import cn.bili.linsixu.module_main.R
import cn.bili.linsixu.module_main.databinding.ActivityLifecyclerBinding
import cn.bili.linsixu.module_main.model.LiveDataModel
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * Created by Magic
 * on 2019/1/9.
 */
@Route(path = "/module_main/LifecyclerActivity")
class LifecyclerActivity : BaseActivity<ActivityLifecyclerBinding>() {

    lateinit var liveViewModel: LiveDataModel

    override fun getLayoutId(): Int {
        return R.layout.activity_lifecycler
    }

    override fun initData() {
        liveViewModel = ViewModelProviders.of(this).get<LiveDataModel>(LiveDataModel::class.java)

        subscribe()
    }

    override fun initClick() {
    }

    fun subscribe() {
        val elapsedTimeObserver: Observer<Long> = Observer { it ->
            var newText: String = this@LifecyclerActivity.resources.getString(R.string.seconds, it)
            binding.magicTitleTxt?.text = newText
            MyLog.i(this.javaClass.name,"newText="+newText)
        }
        liveViewModel.mElapsedTime.observe(this,elapsedTimeObserver)
    }
}