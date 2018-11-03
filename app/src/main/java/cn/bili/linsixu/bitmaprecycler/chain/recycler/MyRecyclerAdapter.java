package cn.bili.linsixu.bitmaprecycler.chain.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bili.linsixu.bitmaprecycler.R;
import cn.bili.linsixu.bitmaprecycler.chain.adapter.RecyclerAdapter;

/**
 * Created by Magic
 * on 2018/11/2.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>{

    private List<String> myName = new ArrayList<>();
    private Context context;

    public MyRecyclerAdapter(List<String> myName, Context context) {
        this.myName = myName;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MyViewHolder(inflater.inflate(R.layout.item_test_name,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(myName.get(position));
    }

    @Override
    public int getItemCount() {
        return myName.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.item_tv);
        }
        private void bind(String name){
            txtName.setText(name);
        }

    }
}
