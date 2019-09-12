package cn.bili.linsixu.commen_base.onshow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import cn.bili.linsixu.commen_base.R
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * Created by Magic
 * on 2019-08-02.
 * email: linsixu@bilibili.com
 */
@Route(path = "/common/ShowReportActivity")
class ShowReportActivity : AppCompatActivity() {
    private lateinit var btnAdd: Button
    private lateinit var mRecycler: RecyclerView
    private val mutableList = arrayListOf<String>()
    private lateinit var mAdapter: MyReportAdapter
    private var mRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_show)
        btnAdd = findViewById(R.id.btn_add)
        mRecycler = findViewById(R.id.recyclerView)

        for (i in 0 until 10) {
            mutableList.add("测试$i")
        }

        mAdapter = MyReportAdapter(mutableList)

        mRecycler.layoutManager = LinearLayoutManager(this)
        mRecycler.adapter = mAdapter

        btnAdd.setOnClickListener {
            mutableList.add(0, "TM")
            mAdapter.notifyItemInserted(0)
            mRefresh = true
        }

        mRecycler.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                if (mRefresh) {
                    mRefresh = false
                    mRecycler.smoothScrollToPosition(0)
                }
            }
        })
    }
}