package cn.bili.linsixu.commen_base.section;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Magic
 * on 2019-06-03.
 * email: linsixu@bilibili.com
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public abstract BaseViewHolder createHolder(ViewGroup parent, int viewType);

    public abstract void bindHolder(BaseViewHolder holder, int position, View itemView);

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = createHolder(parent, viewType);

        handleClick(holder);
        if (mHandleClickListener != null) {
            mHandleClickListener.handleClick(holder);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindHolder(holder, position, holder.itemView);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 覆写这个方法处理点击事件
     * <pre>
     * public void handleClick(BaseViewHolder holder) {
     *       holder.itemView.setOnClickListener(new View.OnClickListener() {
     *          public void onClick(View v) {
     *          }
     *      });
     * }
     * </pre>
     */
    public void handleClick(BaseViewHolder holder) {
    }

    public @Nullable
    HandleClickListener mHandleClickListener;

    public interface HandleClickListener {
        void handleClick(BaseViewHolder holder);
    }

    public void setHandleClickListener(HandleClickListener handleClickListener) {
        mHandleClickListener = handleClickListener;
    }
}
