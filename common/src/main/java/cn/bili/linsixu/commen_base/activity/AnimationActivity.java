package cn.bili.linsixu.commen_base.activity;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.bili.linsixu.commen_base.R;
import cn.bili.linsixu.commen_base.base.BaseActivity;
import cn.bili.linsixu.commen_base.databinding.ActivityAnimationLayoutBinding;

/**
 * Created by Magic
 * on 2019-09-09.
 * email: linsixu@bilibili.com
 */
@Route(path = "/common/AnimationActivity")
public class AnimationActivity extends BaseActivity<ActivityAnimationLayoutBinding> {
    private Animation inAnimation = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animation_layout;
    }

    @Override
    protected void initData() {
        inAnimation = createTopInAnim();
    }

    private Animation createTopInAnim() {
        TranslateAnimation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(300);
        anim.setFillAfter(false);
        return anim;
    }

    @Override
    protected void initClick() {
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                binding.textAnimation.setVisibility(View.GONE);
                RotateAnimation animation = new RotateAnimation(180.0f, -360.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(3000);
                binding.textAnimation.startAnimation(animation);
            }
        });
    }
}
