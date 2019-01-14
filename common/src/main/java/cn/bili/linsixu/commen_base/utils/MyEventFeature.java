/*
 * bilibili:android studio write this method in 19-1-14 下午4:45
 */

package cn.bili.linsixu.commen_base.utils;

import java.util.ArrayList;

import cn.bili.linsixu.commen_base.adapter.ShowAdapter;
import cn.bili.linsixu.commen_base.eventbus.bean.Feature;


public class MyEventFeature {
    public static final String SHOW_SETTING = "feature_show";

    public static final String DEMAND_SETTING = "feature_demand";

    public static final String CONTROLLER_SETTING = "feature_controller";

    public static final ArrayList<Feature> DEFAULT_FEATURE = new ArrayList<>();


    //静态语句块添加adapter
    static {
        DEFAULT_FEATURE.add(new Feature(SHOW_SETTING,ShowAdapter.class));
//        DEFAULT_FEATURE.add(new Feature(DEMAND_SETTING,DemandAdapter.class));
//        DEFAULT_FEATURE.add(new Feature(CONTROLLER_SETTING,ControllerAdapter.class));
    }
}
