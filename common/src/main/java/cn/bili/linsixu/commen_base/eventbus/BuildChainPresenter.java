/*
 * bilibili:android studio write this method in 19-1-14 下午4:41
 */

package cn.bili.linsixu.commen_base.eventbus;

import java.util.ArrayList;

import cn.bili.linsixu.bitmaprecycler.chain.inteface.IPlayerConfiguration;
import cn.bili.linsixu.commen_base.adapter.ShowAdapter;
import cn.bili.linsixu.commen_base.eventbus.bean.Feature;
import cn.bili.linsixu.commen_base.utils.MyEventFeature;


public class BuildChainPresenter {

    private boolean mIsDefault;

    protected IPlayerConfiguration mConfig;

    public BuildChainPresenter() {
    }

    public BuildChainPresenter(IPlayerConfiguration mConfig) {
        this.mConfig = mConfig;
    }

    /**
     * 返回链表的双向指针
     *
     * @return 头指针
     */
    public AbsPlayerAdapter getRootChain(boolean isDefault) {
        mIsDefault = isDefault;
        return buildDefaultChain().build();
    }

    /**
     * 返回双向链表的单例
     *
     * @return
     */
    private PlayerAdapterFactory.AdapterChainBuilder buildDefaultChain() {
        if (mConfig != null) {
            ArrayList<Feature> customFeatures = mConfig.getCustomFeatures();
            ArrayList<String> disabledDefaultFeatures = mConfig.getDisabledDefaultFeatures();
            for (Feature feature : MyEventFeature.DEFAULT_FEATURE) {
                if (disabledDefaultFeatures == null || disabledDefaultFeatures.isEmpty() || !disabledDefaultFeatures.contains(feature.getmName())) {
                    PlayerAdapterFactory.mBuild.put(feature.getmClass());
                }
            }
            if (customFeatures != null && !customFeatures.isEmpty()) {
                for (Feature feature : customFeatures) {
                    PlayerAdapterFactory.mBuild.put(feature.getmClass());
                }
            }
            return PlayerAdapterFactory.mBuild;
        }else {
            return buildDefault(PlayerAdapterFactory.mBuild);
        }
    }


    private PlayerAdapterFactory.AdapterChainBuilder buildDefault(PlayerAdapterFactory.AdapterChainBuilder builder) {
        //            .put(new DemandSwitchScreenPlayerAdapter()) // 重力感应响应
//                    //.put(new PayMovieAdapter())
//                    .put(new DemandDanmakuPlayerAdapter())      // 弹幕控制
//                    .put(new PlayerActionPlayerAdapter())       // 播放完成控制
//                    .put(new DanmakuOptionsPlayerAdapterV2())     // 弹幕设置
//                    .put(new PlayerOptionsPlayerAdapter())      // 播放设置
//                    .put(new PageSelectorPlayerAdapter())       // 选集
//                    .put(new LockablePlayerAdapter())           // 锁屏
//                    .put(new QualitySwitchablePlayerAdapter())  // 清晰度
//                    .put(new DemandGesturePlayerAdapter())      // 手势相关控制
//                    .put(new DemandServiceBindAdapter())        // 后台恢复
//                    .put(new FreeDataNetworkStatePlayerAdapter())  // 网络状态提醒
//                    .put(new NaviHiderPlayerAdapter())          // NavigationBar
//                    .put(new PlayerThumbnailAdapter())          // SeekBar的进度提示
//                    .put(new PlayerToastAdapter())              // Toast提示
//                    .put(new RetryTipsPlayerAdapter())          // 重试
//                    .put(new PayCoinsAdapter())
//                    .put(new DemandScreenshotShareAdapter())    // 截图分享
//                    .put(new DemandDanmakuBlockAdapter())       // 长按选中弹幕举报
//                    .put(new AnalysisAdapter())
//                    .put(new ReportAdapter())                   // 数据上报
//                    .put(new ReportV2Adapter())                 // 数据上报V2
//                    .put(new DemandDanmakuSocketAdapter())      // 实时弹幕池
//                    .put(new DemandSleepModeAdapter())
//                    .put(new KVOPlayerAdapter());
        return builder.put(new ShowAdapter());
    }
}
