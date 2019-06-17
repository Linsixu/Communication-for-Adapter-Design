package cn.bili.linsixu.commen_base.section.use;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cn.bili.linsixu.commen_base.R;
import cn.bili.linsixu.commen_base.section.BaseAdapter;
import cn.bili.linsixu.commen_base.section.BaseSectionAdapter;
import cn.bili.linsixu.commen_base.section.BaseViewHolder;
import cn.bili.linsixu.commen_base.section.bean.TestDataBean;
import cn.bili.linsixu.commen_base.section.bean.TestNotTitleBean;

/**
 * Created by Magic
 * on 2019-06-03.
 * email: linsixu@bilibili.com
 */
public class StarDustSearchAdapter extends BaseSectionAdapter {
    private static final int TYPE_TITLE = 1;
    private static final int TYPE_NO_TITLE = 2;

    private static final int TYPE_PRE_RECOMMEND_SECTION_HEADER = 3;
    private static final int TYPE_PRE_RECOMMEND = 4;
    private static final int TYPE_SECTION_FOOTER = 5;

    private WeakReference<Context> mContext;

    private List<TestDataBean.DataBean> mList;
    private List<TestNotTitleBean> mListNotTitle;

    public StarDustSearchAdapter(Context mContext) {
        this.mContext = new WeakReference<>(mContext);
    }

    @Override
    protected void fillSectionList(SectionManager sectionManager) {
        int listSize = mListNotTitle == null || mListNotTitle.size() <= 0 ? 0 : 1;
        sectionManager.addSectionWithNone(listSize, TYPE_NO_TITLE);
        if (mList != null) {
            for (TestDataBean.DataBean itemsBean : mList) {
                int preRecommendSize = itemsBean == null ? 0 : itemsBean.items.size();
                sectionManager.addSection(preRecommendSize, TYPE_PRE_RECOMMEND, TYPE_PRE_RECOMMEND_SECTION_HEADER, TYPE_SECTION_FOOTER);
            }
        }
    }

    @Override
    public BaseViewHolder createHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NO_TITLE) {
            return NotTitleHolder.create(parent, this);
        } else if (viewType == TYPE_PRE_RECOMMEND) {
            return TestDataContentHolder.create(parent, this);
        } else if (viewType == TYPE_PRE_RECOMMEND_SECTION_HEADER) {
            return TestDataTitleHolder.create(parent, this);
        } else if (viewType == TYPE_SECTION_FOOTER) {
            return TestDataBottomHolder.create(parent,this);
        }
        return null;
    }

    @Override
    public void bindHolder(BaseViewHolder holder, int position, View itemView) {
        if (holder instanceof NotTitleHolder && mListNotTitle != null) {
            ((NotTitleHolder) holder).bind(mListNotTitle.get(position));
        } else if(holder instanceof TestDataTitleHolder && mList != null){
            int sectionIndex = getSectionIndex(position) - 1;
            TestDataBean.DataBean.ItemsBean.ItemBean dataBean = mList.get(sectionIndex).items.get(sectionIndex).item.get(sectionIndex);
            ((TestDataTitleHolder) holder).bind(dataBean);
        } else if(holder instanceof TestDataContentHolder && mList != null){
            int sectionIndex = getSectionIndex(position) - 1;
            TestDataBean.DataBean.ItemsBean.ItemBean dataBean = mList.get(sectionIndex).items.get(sectionIndex).item.get(sectionIndex);
            ((TestDataContentHolder) holder).bind(dataBean);
        } else if(holder instanceof TestDataBottomHolder){
            int sectionIndex = getSectionIndex(position) - 1;
            TestDataBean.DataBean.ItemsBean.ItemBean dataBean = mList.get(sectionIndex).items.get(sectionIndex).item.get(sectionIndex);
            ((TestDataBottomHolder) holder).bind(dataBean);
        }
    }


//    public void updateTestData(ArrayList<TestDataBean> lists) {
//        this.mList = lists;
//        notifySectionData();
//    }

    public void updateTestDate(ArrayList<TestNotTitleBean> lists) {
        mListNotTitle = lists;
        notifySectionData();
    }


    static class NotTitleHolder extends BaseViewHolder<TestNotTitleBean> {
        private TextView mNoTitle;

        public NotTitleHolder(View itemView, BaseAdapter adapter) {
            super(itemView, adapter);
            mNoTitle = itemView.findViewById(R.id.txt_no_title);
        }

        public static TestDataContentHolder create(ViewGroup parent, BaseAdapter adapter) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_section_not_title, parent, false);
            return new TestDataContentHolder(view, adapter);
        }

        @Override
        protected void bind(TestNotTitleBean testDataBean) {
            mNoTitle.setText(testDataBean.time);
        }
    }


    static class TestDataContentHolder extends BaseViewHolder<TestDataBean.DataBean.ItemsBean.ItemBean> {
        private TextView mText;

        public TestDataContentHolder(View itemView, BaseAdapter adapter) {
            super(itemView, adapter);
            mText = itemView.findViewById(R.id.txt_section);
        }

        public static TestDataContentHolder create(ViewGroup parent, BaseAdapter adapter) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_setion_test_content, parent, false);
            return new TestDataContentHolder(view, adapter);
        }

        @Override
        protected void bind(TestDataBean.DataBean.ItemsBean.ItemBean itemsBean) {
            mText.setText(itemsBean.title);
        }
    }

    static class TestDataTitleHolder extends BaseViewHolder<TestDataBean.DataBean.ItemsBean.ItemBean> {
        private TextView textView;

        public TestDataTitleHolder(View itemView, BaseAdapter adapter) {
            super(itemView, adapter);
            textView = itemView.findViewById(R.id.txt_section_title);
        }

        public static TestDataContentHolder create(ViewGroup parent, BaseAdapter adapter) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_section_test_title, parent, false);
            return new TestDataContentHolder(view, adapter);
        }

        @Override
        protected void bind(TestDataBean.DataBean.ItemsBean.ItemBean itemsBean) {
            textView.setText(itemsBean.author);
        }
    }

    static class TestDataBottomHolder extends BaseViewHolder<TestDataBean.DataBean.ItemsBean.ItemBean> {
        public TestDataBottomHolder(View itemView, BaseAdapter adapter) {
            super(itemView, adapter);
        }

        public static TestDataContentHolder create(ViewGroup parent, BaseAdapter adapter) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_section_test_bottom, parent, false);
            return new TestDataContentHolder(view, adapter);
        }

        @Override
        protected void bind(TestDataBean.DataBean.ItemsBean.ItemBean itemsBean) {

        }
    }


}
