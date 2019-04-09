package cn.bili.linsixu.commen_base.feed.adapter;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import cn.bili.linsixu.commen_base.feed.BaseIndexItem;
import cn.bili.linsixu.commen_base.feed.base.BasePegasusHolder;
import cn.bili.linsixu.commen_base.feed.card.BaseCardManager;

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
public class IndexAdapter extends BaseCardAdapter<BasePegasusHolder<BaseIndexItem>> {
    private BaseCardManager mManager;
    public IndexAdapter(@NotNull BaseCardManager mCardManager) {
        super(mCardManager);
        mManager = mCardManager;
    }

    @Override
    public void onBindViewHolder(@NonNull BasePegasusHolder<BaseIndexItem> holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mManager.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return mManager.getItemViewType(position);
    }
}
