/*
 * bilibili:android studio write this method in 19-1-14 下午4:46
 */

package cn.bili.linsixu.commen_base.adapter

import cn.bili.linsixu.commen_base.eventbus.AbsPlayerAdapter
import cn.bili.linsixu.commen_base.eventbus.event.MyEvent
import cn.bili.linsixu.commen_base.utils.MyLog

/**
 * Created by Magic
 * on 2019/1/14.
 */
public class ShowAdapter : AbsPlayerAdapter<ShowAdapter>() {

    val TAG: String = "ShowAdapter"

    override fun onEvent(event: String?, vararg args: Any?) {
        super.onEvent(event, *args)
        if(args.size > 0){
            MyLog.i(TAG,TAG+"接收到信息,"+"信息内容："+args[0])
        }
        if(event.equals(MyEvent.SHOW_MESSAGE)){
            MyLog.i(TAG,TAG+"：这是我需要处理的信息")
        }
    }

    override fun startRegister() {
        super.startRegister()
        registerEvent(this, MyEvent.DEMAND_URL,MyEvent.RECYCLER_URL,MyEvent.CONTROLLER_URL,MyEvent.SEND_MESSAGE)
    }

}