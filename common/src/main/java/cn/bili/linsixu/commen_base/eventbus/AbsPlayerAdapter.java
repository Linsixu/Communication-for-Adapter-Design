/*
 * bilibili:android studio write this method in 19-1-14 下午4:39
 */

package cn.bili.linsixu.commen_base.eventbus;


import cn.bili.linsixu.commen_base.eventbus.inteface.IEventCenter;

public class AbsPlayerAdapter<T extends AbsPlayerAdapter> implements IEventCenter.Receiver {

    protected T mParentAdapter;
    protected T mChildAdapter;
    protected IEventCenter mCenter;

    public AbsPlayerAdapter() {
        super();
    }

    /**
     * 用于将事件从Parent传递到Child下面去
     * @param event 事件类型
     * @param datas 携带数据
     */
    public void onReceiveEvent(String event,Object... datas){
        if(mChildAdapter != null){
            mChildAdapter.onReceiveEvent(event,datas);
        }
    }

    /**
     * 将事件通过EventCenter存储起来
     * @param event 事件类型
     * @param datas 携带数据
     */

    public void postEvent(String event,Object... datas){
        if(mCenter != null){
            mCenter.sendEvent(event,datas);
        }
    }

    @Override
    public void onEvent(String event, Object... args) {

    }

    /**
     * 给所有继承AbsAdapter的子类提供注册监听事件的方法
     * @param receiver 具体接口回调
     * @param events 事件类型
     */
    public void registerEvent(EventCenter.Receiver receiver,String... events){
        if(mCenter != null){
            mCenter.register(receiver,events);
        }
    }

    /**
     * 注销监听
     * @param receiver
     */
    public void unregisterEvent(EventCenter.Receiver receiver){
        if(mCenter != null){
            mCenter.unregister(receiver);
        }
    }

    /**
     * 设置每个Adapter接受消息回调的接口
     * @param iEventCenter
     */
    public void setEventCenter(IEventCenter iEventCenter){
        mCenter = iEventCenter;
    }

    /**
     * 传进来的adapter与当前的AbsPlayerAdapter的子类建立链表关系
     * @param adapter 需要加入链表的adapter
     * @return 返回当前的AbsPlayerAdapter的子类
     */
    public AbsPlayerAdapter attach(T adapter){
        mChildAdapter = adapter;
        mChildAdapter.setParentAdapter(this);
        return this;
    }

    /**
     * 为当前的AbsPlayerAdapter子类设置父指针
     * @param adapter 链表中作为当前的AbsPlayerAdapter子类的父指针
     */
    protected void setParentAdapter(T adapter){
        mParentAdapter = adapter;
    }

    /**
     * 让子类adapter注册监听事件
     */
    public void startRegister(){

    }
}
