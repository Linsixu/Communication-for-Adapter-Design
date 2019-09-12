package cn.bili.linsixu.commen_base.onshow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.bili.linsixu.commen_base.R;

/**
 * Created by Magic
 * on 2019-08-02.
 * email: linsixu@bilibili.com
 */
public class MyReportAdapter extends RecyclerView.Adapter<MyReportAdapter.MyHolder> {
    private List<String> lists;

    public MyReportAdapter(List<String> lists) {
        this.lists = lists;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_show, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.bind(lists.get(position));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_show);

        }

        public void bind(String text) {
            textView.setText(text);
        }
    }
}
