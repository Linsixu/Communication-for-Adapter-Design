package cn.bili.linsixu.bitmaprecycler.chain.adapter;

import cn.bili.linsixu.bitmaprecycler.chain.utils.MyLog;
import cn.bili.linsixu.commen_base.eventbus.AbsPlayerAdapter;
import cn.bili.linsixu.commen_base.eventbus.event.MyEvent;

/**
 * Created by Magic
 * on 2018/10/9.
 */
public class RecyclerAdapter extends AbsPlayerAdapter {
    private final String TAG = "RecyclerAdapter";


    /**
     * 接收来自双向链表中的每个事件
     * @param event 事件类型
     * @param args 携带数据
     */
    @Override
    public void onEvent(String event, Object... args) {
        super.onEvent(event, args);
        if(args.length > 0){
            MyLog.i(TAG,TAG+"接收到信息,"+"信息内容："+args[0]);
        }
        if(event.equals(MyEvent.RECYCLER_URL)){
            MyLog.i(TAG,TAG+"：这是我需要处理的信息");
        }
    }

    @Override
    public void startRegister() {
        super.startRegister();
        //注册你需要监听哪些事件信息（我这里是全部事件都注册了）
        registerEvent(this,MyEvent.DEMAND_URL,MyEvent.RECYCLER_URL,MyEvent.CONTROLLER_URL,MyEvent.SEND_MESSAGE);
    }
}
