package cn.bili.linsixu.module_main;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.nio.file.Path;

import cn.bili.linsixu.commen_base.base.BaseActivity;
import cn.bili.linsixu.module_main.databinding.ActivityRouterBinding;

/**
 * Created by Magic
 * on 2018/11/22.
 */
@Route(path = "/module_main/MainActivity")
public class MainActivity extends BaseActivity<ActivityRouterBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_router;
    }

    @Override
    protected void initData() {
        binding.addBtnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initClick() {

    }
}
