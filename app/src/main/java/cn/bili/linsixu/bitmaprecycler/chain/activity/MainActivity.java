package cn.bili.linsixu.bitmaprecycler.chain.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import cn.bili.linsixu.bitmaprecycler.R;
import cn.bili.linsixu.bitmaprecycler.chain.adapter.ControllerAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.adapter.DemandAdapter;
import cn.bili.linsixu.bitmaprecycler.chain.configuration.AvPlayerConfiguration;
import cn.bili.linsixu.bitmaprecycler.chain.utils.MyEventFeature;
import cn.bili.linsixu.bitmaprecycler.chain.utils.MyLog;
import cn.bili.linsixu.bitmaprecycler.databinding.ActivityMainBinding;
import cn.bili.linsixu.commen_base.base.BaseActivity;
import cn.bili.linsixu.commen_base.eventbus.AbsPlayerAdapter;
import cn.bili.linsixu.commen_base.eventbus.BuildChainPresenter;
import cn.bili.linsixu.commen_base.eventbus.bean.Feature;
import cn.bili.linsixu.commen_base.eventbus.event.MyEvent;
import cn.bili.linsixu.commen_base.utils.DensityUtil;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    BuildChainPresenter presenter = new BuildChainPresenter();
    AbsPlayerAdapter root;

    private PopupWindow popupWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        presenter = new BuildChainPresenter(new AvPlayerConfiguration());
        root = presenter.getRootChain(true);

//        for(Feature f : new AvPlayerConfiguration().getCustomFeatures()){
//            try {
//                presenter.addAdapter((AbsPlayerAdapter)f.getmClass().newInstance());
//                presenter
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        presenter.addListAdapter();

    }

    @Override
    protected void initClick() {
        binding.btnDemand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.postEvent(MyEvent.DEMAND_URL, binding.btnDemand.getText());
//                MyTest1.Companion.print();
            }
        });

        binding.btnController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                root.postEvent(MyEvent.CONTROLLER_URL,binding.btnController.getText());
//                showTips(binding.btnController);
                Uri uri = Uri.parse("/module_main/LifecyclerActivity");
                ARouter.getInstance().build(uri).
                        withString("key","6666").
                        navigation();
            }
        });

        binding.btnRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.postEvent(MyEvent.RECYCLER_URL, binding.btnRecycler.getText());
//                setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
    }

    private void showTips(View view) {
        View tipsView = View.inflate(this, R.layout.popup_item_view, null);

        if (popupWindow == null) {
            popupWindow = new PopupWindow(tipsView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            popupWindow.setOutsideTouchable(true);

            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        int location[] = new int[2];
        view.getLocationOnScreen(location);

        // 以View左上角为参考系,true View全部或者部分可见;false View全部不可见
        boolean localVisibleRect = view.getLocalVisibleRect(new Rect());

        if (localVisibleRect) {

            int w = DensityUtil.dp2px(this, 36);

            popupWindow.showAsDropDown(view, -w, 0);

//            popupWindow.showAtLocation(view,Gravity.START | Gravity.TOP,
//                    location[0] + view.getWidth() / 2,
//                    location[1] + view.getHeight());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MyLog.i("magic","A="+newConfig.orientation);
    }
}
