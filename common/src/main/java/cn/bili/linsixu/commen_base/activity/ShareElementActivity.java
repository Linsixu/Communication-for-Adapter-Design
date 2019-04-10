package cn.bili.linsixu.commen_base.activity;

import android.support.v4.view.ViewCompat;

import cn.bili.linsixu.commen_base.R;
import cn.bili.linsixu.commen_base.base.BaseActivity;
import cn.bili.linsixu.commen_base.databinding.ActivityShareElementBinding;

/**
 * Created by Magic
 * on 2019/4/10.
 * email: linsixu@bilibili.com
 */
public class ShareElementActivity extends BaseActivity<ActivityShareElementBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_element;
    }

    @Override
    protected void initData() {
        ViewCompat.setTransitionName(binding.layout, FeedActivity.LAYOUR_FLAG);
        ViewCompat.setTransitionName(binding.txtFeed, FeedActivity.TXT_FLAG);
    }

    @Override
    protected void initClick() {

    }
}
