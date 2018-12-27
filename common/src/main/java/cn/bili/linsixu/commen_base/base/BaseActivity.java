package cn.bili.linsixu.commen_base.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Magic
 * on 2018/10/10.
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected T binding;

    private String mActivityJumpTag;
    private long mActivityJumpTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (T) DataBindingUtil.setContentView(this, getLayoutId());
        initData();
        initClick();
    }


    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initClick();

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (startActivitySelfCheck(intent)) {
            super.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 检查当前 Activity 是否重复跳转了，不需要检查则重写此方法并返回 true 即可
     *
     * @param intent 用于跳转的Intent对象
     * @return 检测通过返回true，检测不通过返回false
     */
    protected boolean startActivitySelfCheck(Intent intent) {
        //默认检查通过
        boolean result = true;
        //标记对象
        String tag;

        if (intent.getComponent() != null) {
            //显示跳转
            tag = intent.getComponent().getClassName();
        } else if (intent.getAction() != null) {
            tag = intent.getAction();
        } else {
            return result;
        }

        if (tag.equals(mActivityJumpTag) && mActivityJumpTime >= SystemClock.uptimeMillis() - 500) {
            // 检查不通过
            result = false;
        }

        // 记录启动标记和时间
        mActivityJumpTag = tag;
        mActivityJumpTime = SystemClock.uptimeMillis();
        return result;
    }
}
