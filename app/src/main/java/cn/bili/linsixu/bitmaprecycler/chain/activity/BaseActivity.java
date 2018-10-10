package cn.bili.linsixu.bitmaprecycler.chain.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Magic
 * on 2018/10/10.
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (T)DataBindingUtil.setContentView(this,getLayoutId());
        initData();
        initClick();
    }


    abstract int getLayoutId();

    abstract void initData();

    abstract void initClick();
}
