package cn.bili.linsixu.bitmaprecycler.chain.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import cn.bili.linsixu.bitmaprecycler.R;
import cn.bili.linsixu.bitmaprecycler.chain.recycler.LinearItemDecoration;
import cn.bili.linsixu.bitmaprecycler.chain.recycler.MyRecyclerAdapter;
import cn.bili.linsixu.bitmaprecycler.databinding.ActivityRecyclerBinding;

/**
 * Created by Magic
 * on 2018/11/2.
 */
public class DecorationActivity extends BaseActivity<ActivityRecyclerBinding> {
    private ArrayList<String> mDatas =new ArrayList<>();
    private MyRecyclerAdapter adapter = null;
    private ImageView imageView;
    @Override
    int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    void initData() {
//        generateDatas();
//        adapter = new MyRecyclerAdapter(mDatas,this);
//        binding.linearRecyclerView.setAdapter(adapter);
//        binding.linearRecyclerView.addItemDecoration(new LinearItemDecoration(this));
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        binding.linearRecyclerView.setLayoutManager(manager);
    }

    @Override
    void initClick() {
//        binding.linearRecyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ARouter.getInstance()
//                        .build("/module/MainActivity")
//                        .navigation();
//            }
//        });
    }

    private void generateDatas(){
        for (int i=0;i<200;i++){
            mDatas.add("第 " + i +" 个item");
        }
    }
}
