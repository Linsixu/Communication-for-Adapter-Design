package cn.bili.linsixu.module_main;

import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;

import cn.bili.linsixu.commen_base.base.BaseActivity;
import cn.bili.linsixu.module_main.adapter.MainRecyclerAdapter;
import cn.bili.linsixu.module_main.bean.RouterBean;
import cn.bili.linsixu.module_main.databinding.ActivityMagicMainBinding;

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
        list.add(new RouterBean("自定义LiveData","/module_main/LoadDataAcitivity"));
        list.add(new RouterBean("ViewPager翻页动画","/common/PagerActivity"));
        list.add(new RouterBean("onScroll","/common/ShowReportActivity"));
        list.add(new RouterBean("旋转动画","/common/AnimationActivity"));

        adapter = new MainRecyclerAdapter(list);

        binding.recyclerOne.setLayoutManager(new LinearLayoutManager(this));

        binding.recyclerOne.setAdapter(adapter);
    }

    @Override
    protected void initClick() {

    }
}
