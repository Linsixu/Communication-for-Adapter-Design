package cn.bili.linsixu.bitmaprecycler.chain.inteface;


public interface IEventCenter {
    void sendEvent(String event,Object... args);

    void register(Receiver receiver,String... events);

    void unregister(Receiver receiver);

    void release();

    interface Receiver{
        void onEvent(String event, Object... args);
    }
}
