package cn.bili.linsixu.module_main;

import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;

import cn.bili.linsixu.commen_base.base.BaseActivity;
import cn.bili.linsixu.module_main.databinding.ActivityRouterBinding;

/**
 * Created by Magic
 * on 2018/11/22.
 */
@Route(path = "/module_main/MainActivity")
public class MainActivity extends BaseActivity<ActivityMagicMainBinding> {
    private ArrayList<RouterBean> list = new ArrayList<>();
    private MainRecyclerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_magic_main;
    }

    @Override
    protected void initData() {
        list.add(new RouterBean("链表","/app/MainActivity"));
        list.add(new RouterBean("liveData","/module_main/LifecyclerActivity"));
        list.add(new RouterBean("Feed","/common/FeedActivity"));

        adapter = new MainRecyclerAdapter(list);

        binding.recyclerOne.setLayoutManager(new LinearLayoutManager(this));

        binding.recyclerOne.setAdapter(adapter);
    }

    @Override
    protected void initClick() {

    }
}
