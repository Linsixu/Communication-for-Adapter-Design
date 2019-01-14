/*
 * bilibili:android studio write this method in 19-1-14 下午4:30
 */

package cn.bili.linsixu.bitmaprecycler.chain.configuration

import cn.bili.linsixu.bitmaprecycler.chain.adapter.ControllerAdapter
import cn.bili.linsixu.bitmaprecycler.chain.adapter.DemandAdapter
import cn.bili.linsixu.bitmaprecycler.chain.adapter.RecyclerAdapter
import cn.bili.linsixu.bitmaprecycler.chain.inteface.IPlayerConfiguration
import cn.bili.linsixu.commen_base.eventbus.bean.Feature

/**
 * Created by Magic
 * on 2019/1/14.
 */
public class AvPlayerConfiguration : IPlayerConfiguration {
    var features: ArrayList<Feature> = ArrayList<Feature>()

    companion object {
        private val FEATURE_CONTROLLER: String = "ControllerAdapter"
        private val FEATURE_DEMAND: String = "DemandAdapter"
        private val FEATURE_RECYCLER: String = "RecyclerAdapter"
    }

    override fun getCustomFeatures(): ArrayList<Feature>? {
        features.add(Feature(FEATURE_CONTROLLER, ControllerAdapter::class.java))
        features.add(Feature(FEATURE_DEMAND,DemandAdapter::class.java))
        features.add(Feature(FEATURE_RECYCLER,RecyclerAdapter::class.java))
        return features
    }

    override fun getDisabledDefaultFeatures(): ArrayList<String>? {
        return null
    }

    override fun onDestroy() {
    }
}