package cn.bili.linsixu.bitmaprecycler.chain.utils;

import java.util.ArrayList;

import cn.bili.linsixu.bitmaprecycler.chain.adapter.ControllerAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.adapter.DemandAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.adapter.RecyclerAdapter;
import cn.bili.linsixu.commen_base.eventbus.bean.Feature;

/**
 * 添加默认的adapter到链表中
 * Created by Magic
 * on 2018/10/11.
 */
public class MyEventFeature {
    public static final String RECYCLER_SETTING = "feature_recycler";

    public static final String DEMAND_SETTING = "feature_demand";

    public static final String CONTROLLER_SETTING = "feature_controller";

    public static final ArrayList<Feature> DEFAULT_FEATURE = new ArrayList<>();


    //静态语句块添加adapter
    static {
        DEFAULT_FEATURE.add(new Feature(RECYCLER_SETTING,RecyclerAdapter.class));
        DEFAULT_FEATURE.add(new Feature(DEMAND_SETTING,DemandAdapter.class));
        DEFAULT_FEATURE.add(new Feature(CONTROLLER_SETTING,ControllerAdapter.class));
    }
}
