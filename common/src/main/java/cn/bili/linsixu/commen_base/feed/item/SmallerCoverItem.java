package cn.bili.linsixu.commen_base.feed.item;

import android.support.annotation.Nullable;

import com.alibaba.fastjson.annotation.JSONField;

import cn.bili.linsixu.commen_base.feed.BaseIndexItem;

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
public class SmallerCoverItem extends BaseIndexItem {
    @Nullable
    @JSONField(name = "book")
    public String book;

    @Nullable
    @JSONField(name = "desc")
    public String desc;


    @Override
    public String toString() {
        return "SmallerCoverItem{" +
                "book='" + book + '\'' +
                ", desc='" + desc + '\'' +
                ", cardType='" + cardType + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
