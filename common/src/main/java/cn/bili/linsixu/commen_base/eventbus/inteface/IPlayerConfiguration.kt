/*
 * bilibili:android studio write this method in 19-1-14 下午4:39
 */

/*
 * bilibili:android studio write this method in 19-1-14 下午4:22
 */

package cn.bili.linsixu.bitmaprecycler.chain.inteface

import cn.bili.linsixu.commen_base.eventbus.bean.Feature
import java.io.Serializable

/**
 * Created by Magic
 * on 2019/1/14.
 */
public interface IPlayerConfiguration : Serializable {

    fun getCustomFeatures():ArrayList<Feature>?

    fun getDisabledDefaultFeatures():ArrayList<String>?

    fun onDestroy()

}