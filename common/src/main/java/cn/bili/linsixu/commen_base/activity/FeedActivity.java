package cn.bili.linsixu.commen_base.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

import cn.bili.linsixu.commen_base.R;
import cn.bili.linsixu.commen_base.base.BaseActivity;
import cn.bili.linsixu.commen_base.databinding.ActivityCommentBinding;
import cn.bili.linsixu.commen_base.feed.BaseIndexItem;
import cn.bili.linsixu.commen_base.feed.adapter.IndexAdapter;
import cn.bili.linsixu.commen_base.feed.base.BasicCardCreatorV2;
import cn.bili.linsixu.commen_base.feed.base.PegasusCardManager;
import cn.bili.linsixu.commen_base.feed.item.LargeCoverV1Item;
import cn.bili.linsixu.commen_base.feed.item.SmallerCoverItem;
import cn.bili.linsixu.commen_base.utils.PolicyEvent;

/**
 * Created by Magic
 * on 2018/11/22.
 */
@Route(path = "/common/FeedActivity")
public class FeedActivity extends BaseActivity<ActivityCommentBinding> {
    final private int mCardCreateType = 0;

    private PegasusCardManager mCardManager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initData() {
        PolicyEvent policyEvent = new PolicyEvent(PolicyEvent.EVENT_SOURCE);

        mCardManager = new PegasusCardManager(new BasicCardCreatorV2(""), mCardCreateType);

//        List<BaseIndexItem> indexItems = JSON.parseArray(json, BaseIndexItem.class);
//
//        for (BaseIndexItem t:indexItems) {
//            mCardManager.addCard(mCardManager.createCard(t));
//        }
        LargeCoverV1Item t = new LargeCoverV1Item();
        t.cover = "http://i2.hdslb.com/bfs/archive/0130dca5b602f1a9d503c8821827b0a99d5eefc4.jpg";
        t.feed = "天马测试1";
        t.cardType = "large_cover_v1";

        SmallerCoverItem s = new SmallerCoverItem();
        s.desc = "肯定崩溃";
        s.book = "看书拉";
        s.cardType = "small_cover_v1";

        mCardManager.addCard(mCardManager.createCard(t));
        mCardManager.addCard(mCardManager.createCard(s));

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        IndexAdapter adapter = new IndexAdapter(mCardManager);

        binding.recycler.setAdapter(adapter);
    }

    @Override
    protected void initClick() {

    }
}
