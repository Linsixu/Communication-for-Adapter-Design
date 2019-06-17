package cn.bili.linsixu.commen_base.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Magic
 * on 2019-06-03.
 * email: linsixu@bilibili.com
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private BaseAdapter mAdapter;

    public BaseViewHolder(View itemView, BaseAdapter adapter) {
        super(itemView);
        mAdapter = adapter;
    }

    public BaseAdapter getAdapter() {
        return mAdapter;
    }


    protected abstract void bind(T t);
}
