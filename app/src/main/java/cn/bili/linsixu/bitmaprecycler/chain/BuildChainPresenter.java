package cn.bili.linsixu.bitmaprecycler.chain;

import cn.bili.linsixu.bitmaprecycler.chain.adapter.ControllerAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.adapter.DemandAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.adapter.RecyclerAdapter;

/**
 * Created by Magic
 * on 2018/10/9.
 */
public class BuildChainPresenter {


    /**
     * 返回链表的双向指针
     * @return 头指针
     */
    public AbsPlayerAdapter getRootChain(){
        return buildDefaultChain().build();
    }


    /**
     * 往链表中添加adapter
     * @param adapter itemAdapter
     */
    public void addAdapter(AbsPlayerAdapter adapter){
        PlayerAdapterFactory.mBuild.put(adapter);
    }

    /**
     * 返回双向链表的单例
     * @return
     */
    private PlayerAdapterFactory.AdapterChainBuilder buildDefaultChain(){
        return PlayerAdapterFactory.mBuild
                .put(new RecyclerAdapter())
                .put(new DemandAdapter())
                .put(new ControllerAdapter());
    }


    public <E>void addList(E e){

    }
}
