package cn.bili.linsixu.commen_base.feed.base

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
object CardType {
    //单列视频、播放器、直播、番剧、频道推荐、up主推荐
    val LARGE_COVER_V1 = Name.LARGE_COVER_V1.hashCode()
    //单列追番、特殊小卡、会员购、音频、游戏下载小卡
    val SMALL_COVER_V1 = Name.SMALL_COVER_V1.hashCode()

    private var sMap: MutableMap<Int, CardTypeEnum>? = mutableMapOf()

    init {
        for (type in CardTypeEnum.values()) {
            sMap?.put(type.value, type)
        }
    }

    @JvmStatic
    fun getTypeEnum(value: Int?): CardTypeEnum? {
        return sMap?.get(value)
    }


    object Name {
        const val LARGE_COVER_V1 = "large_cover_v1"
        const val SMALL_COVER_V1 = "small_cover_v1"
    }
}