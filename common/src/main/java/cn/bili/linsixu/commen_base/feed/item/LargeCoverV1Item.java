package cn.bili.linsixu.commen_base.feed.item;

import android.support.annotation.Keep;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.annotation.JSONField;

import cn.bili.linsixu.commen_base.feed.BaseIndexItem;

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
@Keep
public class LargeCoverV1Item extends BaseIndexItem {
    @Nullable
    @JSONField(name = "cover")
    public String cover;

    @Nullable
    @JSONField(name = "feed")
    public String feed;

    @Override
    public String toString() {
        return "LargeCoverV1Item{" +
                "cover:'" + cover + "\'" +
                ", feed:'" + feed + "\'" +
                ", cardType:'" + cardType + "\'" +
                ", name:'" + name + "\'" +
                ", number:'" + number + "\'" +
                ", address:'" + address + "\'" +
                '}';
    }
}
