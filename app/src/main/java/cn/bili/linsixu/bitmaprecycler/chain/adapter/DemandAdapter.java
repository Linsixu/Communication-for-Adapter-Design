package cn.bili.linsixu.bitmaprecycler.chain.adapter;

import cn.bili.linsixu.bitmaprecycler.chain.AbsPlayerAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.event.MyEvent;
import cn.bili.linsixu.bitmaprecycler.chain.utils.MyLog;

/**
 * Created by Magic
 * on 2018/10/10.
 */
public class DemandAdapter extends AbsPlayerAdapter {

    private final String TAG = "DemandAdapter";



    @Override
    public void onEvent(String event, Object... args) {
        super.onEvent(event, args);
        if(args.length > 0){
            MyLog.i(TAG,TAG+"接收到信息,"+"信息内容："+args[0]);
        }
        if(event.equals(MyEvent.DEMAND_URL)){
            MyLog.i(TAG,TAG+"：这是我需要处理的信息");
        }
    }

    @Override
    public void startRegister() {
        super.startRegister();
        //注册你需要监听哪些事件信息
        registerEvent(this,MyEvent.DEMAND_URL,MyEvent.RECYCLER_URL,MyEvent.CONTROLLER_URL,MyEvent.SEND_MESSAGE);
    }
}
