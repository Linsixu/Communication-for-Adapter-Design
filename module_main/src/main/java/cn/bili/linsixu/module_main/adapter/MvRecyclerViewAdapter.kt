package cn.bili.linsixu.module_main.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import cn.bili.linsixu.module_main.R
import cn.bili.linsixu.module_main.bean.UserName
import cn.bili.linsixu.module_main.vmodel.UserNameViewModel
import cn.bili.linsixu.module_main.vmodel.helper.ListDataNotifyHelper

/**
 * Created by Magic
 * on 2019/2/27.
 * email: linsixu@bilibili.com
 */
class MvRecyclerViewAdapter(var list: ArrayList<UserName>) :
    RecyclerView.Adapter<MvRecyclerViewAdapter.UserNameHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserNameHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_user_messgae, parent, false)
        return UserNameHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserNameHolder?, position: Int) {
        holder?.txtName?.text = list[position].name
        holder?.txtAdress?.text = list[position].address
        holder?.txtNumber?.text = list[position].number.toString()
        holder?.btnUpdate?.setOnClickListener {
            ListDataNotifyHelper.updateValue(holder.itemView.context as? Activity, list,UserName("成功修改", "", 0),position)
            UserNameViewModel.clickUpdateMsg(holder.itemView.context as? Activity, position)
        }
    }

    class UserNameHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView?.findViewById<TextView>(R.id.txt_userName)
        val txtAdress = itemView?.findViewById<TextView>(R.id.txt_userAddress)
        val txtNumber = itemView?.findViewById<TextView>(R.id.txt_userNumber)
        val btnUpdate = itemView?.findViewById<Button>(R.id.btn_update)
    }
}