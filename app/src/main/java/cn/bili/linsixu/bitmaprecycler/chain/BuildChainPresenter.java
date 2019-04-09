package cn.bili.linsixu.bitmaprecycler.chain;

import java.util.ArrayList;

import cn.bili.linsixu.bitmaprecycler.chain.adapter.ControllerAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.adapter.DemandAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.adapter.RecyclerAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.bean.Feature;
import cn.bili.linsixu.bitmaprecycler.chain.event.MyEvent;
import cn.bili.linsixu.bitmaprecycler.chain.utils.MyEventFeature;

/**
 * Created by Magic
 * on 2018/10/9.
 */
public class BuildChainPresenter {

    private ArrayList<String> disableFeature = new ArrayList<>();

    /**
     * 返回链表的双向指针
     * @return 头指针
     */
    public AbsPlayerAdapter getRootChain(boolean isDefault){
        return buildDefaultChain(isDefault).build();
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
    private PlayerAdapterFactory.AdapterChainBuilder buildDefaultChain(boolean isDefault){
        if(!isDefault){
            if(disableFeature.size() > 0){
                for(Feature feature:MyEventFeature.DEFAULT_FEATURE){
                    if(!disableFeature.contains(feature.getmName())){ //如果有需要移除监听的adapter，就不添加到链表中
                        PlayerAdapterFactory.mBuild.put(feature.getmClass());
                    }
                }
            }
            return PlayerAdapterFactory.mBuild;
        }
        return PlayerAdapterFactory.mBuild
                .put(new RecyclerAdapter())
                .put(new DemandAdapter())
                .put(new ControllerAdapter());
    }


    /**
     * 添加需要从默认链表中移除的
     * @param featureName
     */
    public void addDisFeature(String featureName){
        disableFeature.add(featureName);
    }
}
