package cn.bili.linsixu.bitmaprecycler.chain.adapter;

import cn.bili.linsixu.bitmaprecycler.chain.utils.MyLog;
import cn.bili.linsixu.commen_base.eventbus.AbsPlayerAdapter;
import cn.bili.linsixu.commen_base.eventbus.event.MyEvent;

/**
 * Created by Magic
 * on 2018/10/10.
 */
public class ControllerAdapter extends AbsPlayerAdapter {
    private final String TAG = "ControllerAdapter";

    @Override
    public void onEvent(String event, Object... args) {
        super.onEvent(event, args);
        if(args.length > 0){
            MyLog.i(TAG,TAG+"接收到信息,"+"信息内容："+args[0]);
        }
        if(event.equals(MyEvent.CONTROLLER_URL)){
            MyLog.i(TAG,TAG+"：这是我需要处理的信息");
            postEvent(MyEvent.SEND_MESSAGE,"兄弟们，一起搞事情了");
        }
    }


    @Override
    public void startRegister() {
        super.startRegister();
        //注册你需要监听哪些事件信息
        registerEvent(this,MyEvent.DEMAND_URL,MyEvent.RECYCLER_URL,MyEvent.CONTROLLER_URL,MyEvent.SEND_MESSAGE);
    }
}
