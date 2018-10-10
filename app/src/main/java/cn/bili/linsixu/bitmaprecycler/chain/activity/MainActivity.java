package cn.bili.linsixu.bitmaprecycler.chain.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.bili.linsixu.bitmaprecycler.R;
import cn.bili.linsixu.bitmaprecycler.chain.AbsPlayerAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.BuildChainPresenter;
import cn.bili.linsixu.bitmaprecycler.chain.event.MyEvent;
import cn.bili.linsixu.bitmaprecycler.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    BuildChainPresenter presenter = new BuildChainPresenter();
    AbsPlayerAdapter root ;

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    void initData() {
        root = presenter.getRootChain();
    }

    @Override
    void initClick() {
        binding.btnDemand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.postEvent(MyEvent.DEMAND_URL,binding.btnDemand.getText());
            }
        });

        binding.btnController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.postEvent(MyEvent.CONTROLLER_URL,binding.btnController.getText());
            }
        });

        binding.btnRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.postEvent(MyEvent.RECYCLER_URL,binding.btnRecycler.getText());
            }
        });
    }
}
