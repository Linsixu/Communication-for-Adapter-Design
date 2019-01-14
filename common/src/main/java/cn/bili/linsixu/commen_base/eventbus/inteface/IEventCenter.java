/*
 * bilibili:android studio write this method in 19-1-14 下午4:39
 */

package cn.bili.linsixu.commen_base.eventbus.inteface;


public interface IEventCenter {
    void sendEvent(String event, Object... args);

    void register(Receiver receiver, String... events);

    void unregister(Receiver receiver);

    void release();

    interface Receiver{
        void onEvent(String event, Object... args);
    }
}
