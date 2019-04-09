package cn.bili.linsixu.module_main.ui

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import cn.bili.linsixu.commen_base.base.BaseActivity
import cn.bili.linsixu.commen_base.utils.MyLog
import cn.bili.linsixu.module_main.R
import cn.bili.linsixu.module_main.adapter.MvRecyclerViewAdapter
import cn.bili.linsixu.module_main.bean.UserName
import cn.bili.linsixu.module_main.databinding.ActivityMvvmBinding
import cn.bili.linsixu.module_main.vmodel.UserNameViewModel
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * Created by Magic
 * on 2019/2/27.
 * email: linsixu@bilibili.com
 */
@Route(path = "/module_main/MvActivity")
class MvActivity : BaseActivity<ActivityMvvmBinding>() {

    lateinit var listSource: ArrayList<UserName>

    override fun getLayoutId(): Int {
        return R.layout.activity_mvvm
    }

    override fun initData() {
        val manager: LinearLayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = manager

        buildTestData()
//        UserNameViewModel.setListUser(this, listSource)
        binding.recycler.adapter = MvRecyclerViewAdapter(listSource)
    }

    override fun initClick() {
        UserNameViewModel.observeListDataSource(this, Observer {
            MyLog.i("MvActivity","数据源改变了${it.toString()}")
            binding.recycler.adapter.notifyDataSetChanged()
        })


        UserNameViewModel.observeClickUpdate(this, Observer {
            Toast.makeText(this,"item 按钮点击",Toast.LENGTH_SHORT).show()
        })
    }

    fun buildTestData() {
        listSource = ArrayList()
        for (i in 1..20) {
            val user = UserName("1", "AD+$i", i * 10)
            listSource.add(user)
        }
    }
}