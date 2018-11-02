package cn.bili.linsixu.bitmaprecycler.chain.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Magic
 * on 2018/11/1.
 */
public class WaterfallLayout extends ViewGroup {
    private int columns = 3;
    private int hSpace = 20;
    private int vSpace = 20;
    private int childWidth = 0;
    private int top[];
    public WaterfallLayout(Context context) {
        super(context);
        top = new int[columns];
    }

    public WaterfallLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        top = new int[columns];
    }

    public WaterfallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        top = new int[columns];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modelWidth = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        childWidth = (widthSize - (columns - 1) * hSpace) / columns;
        int wrapWidth;
        int count = getChildCount();
        if(count < columns){
            wrapWidth = (count - 1) * hSpace + count * childWidth;
        }else{
            wrapWidth = widthSize;
        }
        clearTop();
        for(int i = 0;i < count;i++){
            View childView = getChildAt(i);
            int childHeight = childView.getMeasuredHeight() / childView.getMeasuredWidth() * childWidth;//等比压缩图片的显示
            int minColums = getMinHeightColum();

            WaterLayoutParam layoutParam = (WaterLayoutParam)childView.getLayoutParams();
            layoutParam.left = minColums * (childWidth + hSpace);
            layoutParam.top = top[minColums];
            layoutParam.right = layoutParam.left + childWidth;
            layoutParam.bottom = layoutParam.top + childHeight;
            top[minColums] += (i / columns) * vSpace + childHeight;
        }
        int wrapHeight = getMaxHeight();
        setMeasuredDimension((MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST)? wrapWidth : widthSize,wrapHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
//        clearTop();
        for(int i = 0; i < count;i++){
            View viewChild = getChildAt(i);
//            int min = getMinHeightColum();
//            int childHeight = viewChild.getMeasuredHeight() / viewChild.getMeasuredWidth() * childWidth;//等比压缩图片的显示
            WaterLayoutParam param = (WaterLayoutParam)viewChild.getLayoutParams();
//            int tLeft = param.left;
//            int tTop = param.top;
//            int tRight = param.right;
//            int tButtom = param.bottom;
//            top[min] += childHeight + vSpace;
            viewChild.layout(param.left,param.top,param.right,param.bottom);
        }
    }

    private void clearTop() {
        for (int i = 0; i < columns; i++) {
            top[i] = 0;
        }
    }

    private int getMinHeightColum(){
        int min = 0;
        for(int i = 0; i < top.length;i++){
            if(top[min] > top[i])min = i;
        }
        return min;
    }

    private int getMaxHeight(){
        int max = 0;
        for(int i = 0; i < columns;i++){
            if(top[max] < top[i])max = i;
        }
        return top[max];
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int index);
    }

    public void setOnItemClickListener(final OnItemClickListener listener) {
        for (int i = 0; i < getChildCount(); i++) {
            final int index = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, index);
                }
            });
        }
    }


    /**
     * 保存每个子View的坐标
     */
    public static class WaterLayoutParam extends ViewGroup.LayoutParams{
        public int left = 0;
        public int top = 0;
        public int right = 0;
        public int bottom = 0;

        public WaterLayoutParam(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public WaterLayoutParam(int width, int height) {
            super(width, height);
        }

        public WaterLayoutParam(LayoutParams source) {
            super(source);
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new WaterLayoutParam(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new WaterLayoutParam(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new WaterLayoutParam(getContext(),attrs);
    }


    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof WaterLayoutParam;
    }
}
