package cn.bili.linsixu.commen_base.section;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import java.util.ArrayList;

/***
 * 将RecyclerView数据源分成片段处理的适配器:支持多个带有header以及footer的section
 * <pre>
 * +---------------------------------+
 * |    section header(Optional)     |
 * +---------------------------------+
 * | +------------+   +------------+ |
 * | |      1     |   |      2     | |
 * | +------------+   +------------+ |
 * |    section footer(Optional)     |
 * +---------------------------------+
 * |    section header(Optional)     |
 * +---------------------------------+
 * | +------------+   +------------+ |
 * | |      1     |   |      2     | |
 * | +------------+   +------------+ |
 * | +------------+   +------------+ |
 * | |      3     |   |      4     | |
 * | +------------+   +------------+ |
 * |    section footer(Optional)     |
 * +---------------------------------+
 */
public abstract class BaseSectionAdapter extends BaseAdapter {
    public static final int TYPE_NONE = -1;
    private SectionManager mSectionManager;
    private SparseArray<Section> mSectionItems = new SparseArray<>();
    private SparseArray<Section> mSectionTypes = new SparseArray<>();
    private int mItemCount;

    public BaseSectionAdapter() {
        mSectionManager = new SectionManager();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        calc();
    }

    @Override
    public final int getItemCount() {
        return mItemCount;
    }

    @Override
    public final int getItemViewType(int position) {
        Section section = getSection(position);
        if (section != null) {
            if (position == section.start && section.headerViewType > 0) {
                return section.headerViewType;
            }

            if (position == section.end && section.footerViewType > 0) {
                return section.footerViewType;
            }

            return section.contentViewType;
        }
        return super.getItemViewType(position);
    }

    private void calc() {
        mSectionItems.clear();
        mItemCount = 0;
        mSectionManager.clear();
        fillSectionList(mSectionManager);
        for (Section section : mSectionManager.mSections) {
            section.start = mItemCount;
            int size = section.size;
            for (int i = 0; i < size; i++) {
                mSectionItems.put(mItemCount + i, section);
            }
            mItemCount += size;
            section.end = mItemCount - 1;
            mSectionTypes.put(section.contentViewType, section);
        }
    }

    /** 添加section数据 */
    public void addSection(int index, Section section) {
        mSectionManager.mSections.add(index, section);
    }

    /** 添加section数据 */
    public void addSection(Section section) {
        mSectionManager.mSections.add(section);
    }

    /** 删除section数据 */
    public void removeSection(int index) {
        mSectionManager.mSections.remove(index);
    }


    /** 刷新section数据 */
    public void notifySectionData(boolean isNeedNotify) {
        calc();

        if (isNeedNotify) {
            notifyDataSetChanged();
        }
    }

    /** 刷新section数据 */
    public void notifySectionData() {
        notifySectionData(true);
    }

    protected abstract void fillSectionList(SectionManager sectionManager);

    /** 获取position所在的section信息 */
    public Section getSection(int position) {
        return mSectionItems.get(position);
    }

    /** 获取position所在的section索引值 */
    public int getSectionIndex(int position) {
        return mSectionManager.mSections.indexOf(mSectionItems.get(position));
    }

    public Section getSectionFromType(int contentViewType) {
        return mSectionTypes.get(contentViewType);
    }

    /** 获取position所在的section中的索引值(去除header) */
    public int getIndexInSection(int position) {
        Section section = mSectionItems.get(position);
        return position - section.start - (section.headerViewType > 0 ? 1 : 0);
    }

    public static class Section {
        /** 包含header和footer的总size */
        public int size;
        /** section的content的size */
        public int contentSize;
        /** section开始位置 */
        public int start;
        /** section结束位置 */
        public int end;
        /** content的ViewType */
        public int contentViewType;
        /** 小于0表示该section没有header */
        public int headerViewType;
        /** 小于0表示该section没有footer */
        public int footerViewType;

        /**
         * Section构造器
         *
         * @param contentSize     section中content个数
         * @param contentViewType section中content的viewType
         * @param headerViewType  section中header的viewType，当headerViewType值为{@value TYPE_NONE}时表示没有header
         * @param footerViewType  section中footer的viewType，当footerViewType值为{@value TYPE_NONE}时表示没有footer
         */
        public Section(int contentSize, int contentViewType, int headerViewType, int footerViewType) {
            this.contentSize = contentSize;
            this.contentViewType = contentViewType;
            this.headerViewType = headerViewType;
            this.footerViewType = footerViewType;
            this.size = contentSize + (headerViewType == TYPE_NONE ? 0 : 1) + (footerViewType == TYPE_NONE ? 0 : 1);
        }
    }

    public static class SectionManager {
        private ArrayList<Section> mSections = new ArrayList<>();

        /**
         * 添加section
         *
         * @param contentSize     section中content个数
         * @param contentViewType section中content的viewType
         * @param headerViewType  section中header的viewType，当headerViewType值为{@value TYPE_NONE}时表示没有header
         * @param footerViewType  section中footer的viewType，当footerViewType值为{@value TYPE_NONE}时表示没有footer
         */
        public void addSection(int contentSize, int contentViewType, int headerViewType, int footerViewType) {
            Section section = new Section(contentSize, contentViewType, headerViewType, footerViewType);
            mSections.add(section);
        }

        /**
         * 添加没有header和footer的section
         *
         * @deprecated use {@link #addSectionWithNone(int, int)} instead
         */
        @Deprecated
        public void addSection(int contentSize, int contentViewType) {
            addSection(contentSize, contentViewType, TYPE_NONE, TYPE_NONE);
        }

        /**
         * 添加带有header的section(没有footer)
         *
         * @param contentSize     section中content个数
         * @param contentViewType section中content的viewType
         * @param headerViewType  section中header的viewType，当headerViewType值为{@value TYPE_NONE}时表示没有header
         */
        public void addSectionWithHeader(int contentSize, int contentViewType, int headerViewType) {
            addSection(contentSize, contentViewType, headerViewType, TYPE_NONE);
        }

        /**
         * 添加带有footer的section(没有header)
         *
         * @param contentSize     section中content个数
         * @param contentViewType section中content的viewType
         * @param footerViewType  section中footer的viewType，当footerViewType值为{@value TYPE_NONE}时表示没有footer
         */
        public void addSectionWithFooter(int contentSize, int contentViewType, int footerViewType) {
            addSection(contentSize, contentViewType, TYPE_NONE, footerViewType);
        }

        /**
         * 添加没有header和footer的section
         *
         * @param contentSize     section中content个数
         * @param contentViewType section中content的viewType
         */
        public void addSectionWithNone(int contentSize, int contentViewType) {
            addSection(contentSize, contentViewType, TYPE_NONE, TYPE_NONE);
        }

        /**
         * 不包括 section 的 header 和 footer
         * @return
         */
        public int getSecionSize() {
            return mSections.size();
        }

        private void clear() {
            mSections.clear();
        }
    }
}

