/*
 * bilibili:android studio write this method in 19-1-23 下午2:09
 */

package cn.bili.linsixu.module_main.ui

import cn.bili.linsixu.commen_base.base.BaseActivity
import cn.bili.linsixu.module_main.R
import cn.bili.linsixu.module_main.databinding.ActivityLifecyclerBinding

/**
 * Created by Magic
 * on 2019/1/23.
 */
class AnnationActivity: BaseActivity<ActivityLifecyclerBinding>(){

    override fun getLayoutId(): Int {
        return R.layout.activity_lifecycler
    }

    override fun initData() {
//        HelloWorld.main("")
    }

    override fun initClick() {

    }
}