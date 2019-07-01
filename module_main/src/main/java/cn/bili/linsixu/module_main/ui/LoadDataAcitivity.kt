package cn.bili.linsixu.module_main.ui

import android.arch.lifecycle.Observer
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import cn.bili.linsixu.commen_base.base.BaseActivity
import cn.bili.linsixu.commen_base.utils.MyLog
import cn.bili.linsixu.module_main.R
import cn.bili.linsixu.module_main.bean.LiveDataBean
import cn.bili.linsixu.module_main.databinding.ActivityLoadDataBinding
import cn.bili.linsixu.module_main.model.LiveDataCallBack
import cn.bili.linsixu.module_main.model.LoadDataViewModel
import cn.bili.linsixu.module_main.model.Status
import cn.bili.linsixu.module_main.parser.LiveDataParser
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * Created by Magic
 * on 2019-07-01.
 * email: linsixu@bilibili.com
 */
@Route(path = "/module_main/LoadDataAcitivity")
class LoadDataAcitivity : BaseActivity<ActivityLoadDataBinding>() {
    private var mViewModel: LoadDataViewModel? = null
    private val mHandler = Handler(Looper.getMainLooper())

    override fun getLayoutId(): Int {
        return R.layout.activity_load_data
    }

    /**
     * 通过状态区分加载数据情况
     */
    override fun initData() {
        mViewModel = LoadDataViewModel.getViewModel(this)
        mViewModel?.dataTest?.observe(this, Observer<LiveDataCallBack<LiveDataBean>> {
            switchData(it)
        })

        binding.btnError.setOnClickListener {
            mHandler.postDelayed(object : Runnable {
                override fun run() {
                    mViewModel?.errorData(IllegalAccessException("非法参数"))
                }
            }, 3000)
        }

        binding.btnLoading.setOnClickListener {
            mHandler.postDelayed(object : Runnable {
                override fun run() {
                    mViewModel?.loadingData(null)
                }
            }, 3000)
        }

        binding.btnCorrect.setOnClickListener {
            mHandler.postDelayed(object : Runnable {
                override fun run() {
                    val data = LiveDataParser(this@LoadDataAcitivity, "live_data.json").getResponseFromLocal()
                    mViewModel?.loadingData(data)
                }
            }, 3000)
        }
    }

    private fun switchData(data: LiveDataCallBack<LiveDataBean>?) {
        data?.let {
            when (data.status) {
                Status.SUCCESS -> MyLog.i("magic", "success=" + it.data?.data.toString())
                Status.ERROR -> MyLog.i("magic", "error=" + it.error)
                Status.LOADING -> MyLog.i("magic", "loading" + it.data?.data?.items.toString())
            }
        }
    }

    override fun initClick() {
    }
}