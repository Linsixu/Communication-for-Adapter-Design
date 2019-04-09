package cn.bili.linsixu.module_main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.bili.linsixu.module_main.R
import cn.bili.linsixu.module_main.bean.RouterBean
import cn.bili.linsixu.module_main.bean.UserName
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by Magic
 * on 2019/4/9.
 * email: linsixu@bilibili.com
 */
class MainRecyclerAdapter(var list: ArrayList<RouterBean>):
    RecyclerView.Adapter<MainRecyclerAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_view_content,parent,false)
        return MainHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.txtName.text = list.get(position).text
        holder.txtName.setOnClickListener(View.OnClickListener {
            ARouter.getInstance().build(list.get(position).routerName).navigation()
        })
    }

    class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.findViewById<TextView>(R.id.txt_name)
    }
}