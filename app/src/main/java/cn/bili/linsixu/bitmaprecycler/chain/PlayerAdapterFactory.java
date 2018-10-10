package cn.bili.linsixu.bitmaprecycler.chain;

import android.util.Log;

import cn.bili.linsixu.bitmaprecycler.chain.inteface.IEventCenter;

/**
 * Created by Magic
 * on 2018/10/9.
 */
public class PlayerAdapterFactory {
    public static final AdapterChainBuilder mBuild = new AdapterChainBuilder();

    //采用内部静态类builder设计模式构建双向链表
    public static class AdapterChainBuilder{
        private static final String TAG = "AdapterChainBuilder";
        AbsPlayerAdapter root;
        AbsPlayerAdapter last;
        IEventCenter mEventCenter;

        /**
         * 往双向链表中添加item
         * @param adapter
         * @return 单例
         */
        public PlayerAdapterFactory.AdapterChainBuilder put(AbsPlayerAdapter adapter){
            if(root == null){ //如果是头指针为空，就将当前插入的item作为头指针
                root = last = adapter;
            }else{
                //如果头指针不为空，从尾部开始插入item，将last标记指针往后移
                last.attach(adapter);
                last = adapter;
            }
            //如果分发Event事件的EventCenter为空，必须要实力化一个
            if(mEventCenter == null){
                mEventCenter = new EventCenter();
            }
            adapter.setEventCenter(mEventCenter);
            adapter.startRegister();//确保了EventCenter不为空的时候才让adapter进行监听事件的注册
            return mBuild;
        }

        /**
         * 根据Class往双向链表中添加item
         * @param adapter 类的名字
         * @param <T> 规定必须是AbsPlayerAdapter的子类
         * @return 单例
         */
        public <T extends AbsPlayerAdapter> PlayerAdapterFactory.AdapterChainBuilder put(Class<T> adapter){
            try {
                put(adapter.newInstance());
            } catch (Throwable e) {
                Log.i(TAG,"Exception when put adapter -> " + e);
            }
            return mBuild;
        }

        /**
         * 根据CLass的名字往双向链表中加item
         * @param className 类的名字
         * @return 单例
         */
        public PlayerAdapterFactory.AdapterChainBuilder put(String className){
            try {
                Class adapter = Class.forName(className);
                put(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mBuild;
        }

        /**
         * 返回双向链表的头部指针
         * @return 头部指针
         */
        public AbsPlayerAdapter build(){
            try {
                return root;
            }finally {
                root = last = null; //reset
                mEventCenter = null;
            }
        }
    }
}
