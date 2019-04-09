package cn.bili.linsixu.commen_base.feed;

import android.support.annotation.Keep;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.annotation.JSONField;

import cn.bili.linsixu.commen_base.feed.card.FeedItem;

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
@Keep
public class BaseIndexItem extends FeedItem {
    @Nullable
    @JSONField(name = "card_type")
    public String cardType;

    @Nullable
    @JSONField(name = "userName")
    public String name;

    @Nullable
    @JSONField(name = "number")
    public String number;

    @Nullable
    @JSONField(name = "address")
    public String address;

}
