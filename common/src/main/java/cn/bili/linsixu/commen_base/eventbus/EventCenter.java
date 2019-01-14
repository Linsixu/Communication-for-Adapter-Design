/*
 * bilibili:android studio write this method in 19-1-14 下午4:41
 */

package cn.bili.linsixu.commen_base.eventbus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.bili.linsixu.commen_base.eventbus.inteface.IEventCenter;


public class EventCenter implements IEventCenter {


    private Map<String,List<IEventCenter.Receiver>> mReceiverMap = Collections.synchronizedMap(new HashMap<String, List<Receiver>>());

    @Override
    public void sendEvent(String event, Object... args) {
        List<Receiver> receiverList = mReceiverMap.get(event);
        if(receiverList == null || receiverList.isEmpty()){
            return;
        }
        for(Receiver receiver : receiverList){
            receiver.onEvent(event,args);
        }
    }

    @Override
    public void register(Receiver receiver, String... events) {
        if(events.length <= 0){
            return;
        }
        for(String event:events){
            List<Receiver> receivers = mReceiverMap.get(event);
            if(receivers == null){
                receivers = new CopyOnWriteArrayList<>();
            }
            if(!receivers.contains(receiver)){
                receivers.add(receiver);
                mReceiverMap.put(event,receivers);
            }
        }
    }

    @Override
    public void unregister(Receiver receiver) {
        Iterator<Map.Entry<String,List<Receiver>>> iterator = mReceiverMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,List<Receiver>> entry = iterator.next();
            List<Receiver> receivers = entry.getValue();
            if(receivers!= null && !receivers.isEmpty() && receivers.contains(receiver)){
                receivers.remove(receiver);
            }
            if(receivers.isEmpty()){
                iterator.remove();
            }
        }
    }

    @Override
    public void release() {
        if(mReceiverMap != null && !mReceiverMap.isEmpty()){
            mReceiverMap.clear();
        }
    }
}
