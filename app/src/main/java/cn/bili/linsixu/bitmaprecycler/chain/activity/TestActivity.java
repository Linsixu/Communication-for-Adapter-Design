package cn.bili.linsixu.bitmaprecycler.chain.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import cn.bili.linsixu.bitmaprecycler.R;
import cn.bili.linsixu.bitmaprecycler.chain.view.WaterfallLayout;
import cn.bili.linsixu.bitmaprecycler.databinding.ActivityWaterBinding;
import cn.bili.linsixu.commen_base.base.BaseActivity;


/**
 * Created by Magic
 * on 2018/11/1.
 */
public class TestActivity extends BaseActivity<ActivityWaterBinding> {
    private static int IMG_COUNT = 5;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_router;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView(binding.waterfallLayout);
            }
        });
    }

    private void addView(WaterfallLayout waterfallLayout){
        Random random = new Random();
        Integer num = Math.abs(random.nextInt());
        WaterfallLayout.LayoutParams layoutParams = new WaterfallLayout.LayoutParams(WaterfallLayout.LayoutParams.WRAP_CONTENT,
                WaterfallLayout.LayoutParams.WRAP_CONTENT);
        ImageView imageView = new ImageView(this);
        if (num % IMG_COUNT == 0) {
            imageView.setImageResource(R.drawable.pic_1);
        } else if (num % IMG_COUNT == 1) {
            imageView.setImageResource(R.drawable.pic_2);
        } else if (num % IMG_COUNT == 2) {
            imageView.setImageResource(R.drawable.pic_3);
        } else if (num % IMG_COUNT == 3) {
            imageView.setImageResource(R.drawable.pic_4);
        } else if (num % IMG_COUNT == 4) {
            imageView.setImageResource(R.drawable.pic_5);
        }else{
            imageView.setImageResource(R.drawable.pic_1);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        waterfallLayout.addView(imageView, layoutParams);

        waterfallLayout.setOnItemClickListener(new WaterfallLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int index) {
                Toast.makeText(TestActivity.this, "item=" + index, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
