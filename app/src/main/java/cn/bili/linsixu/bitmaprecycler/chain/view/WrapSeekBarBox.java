package cn.bili.linsixu.bitmaprecycler.chain.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Magic
 * on 2018/11/1.
 */
public class WrapSeekBarBox extends ViewGroup {
    private int lineW = 0,lineH;
    private int width = 0,height = 0;
    public WrapSeekBarBox(Context context) {
        super(context);
    }

    public WrapSeekBarBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapSeekBarBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthP = MeasureSpec.getSize(widthMeasureSpec);
        int widthM = MeasureSpec.getMode(widthMeasureSpec);
        int heightP = MeasureSpec.getSize(heightMeasureSpec);
        int heightM = MeasureSpec.getMode(heightMeasureSpec);
        int count = getChildCount();
        for(int i = 0; i < count; i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);//调用childView的onMeasure方法

            MarginLayoutParams params = (MarginLayoutParams)child.getLayoutParams();
            int childW = child.getMeasuredWidth() + params.leftMargin +params.rightMargin;
            int childH = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            if((lineW + childW) > widthP){ //换下一行显示
                width = Math.max(lineW,childW);
                height += lineH;

                lineW = childW;
                lineH = childH;
            }else{
                lineH = Math.max(lineH , childH);
                lineW += childW;
            }

            if(i == count -1){
                height += lineH;
                width = Math.max(lineW,width);
            }
        }
        int resultW = (widthM == MeasureSpec.EXACTLY)? widthP : width;
        int resultH = (heightM == MeasureSpec.EXACTLY)?heightP : height;
        setMeasuredDimension(resultW,resultH);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int lineWidth = 0;//累加当前行的行宽
        int lineHeight = 0;//当前行的行高
        int top=0,left=0;//
        int measureWidth = getMeasuredWidth();
        int measureHeight = getMeasuredHeight();
        for(int i = 0;i < count;i++){
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            if(lineWidth + childWidth > measureWidth){
                top += lineHeight;
                left = 0;
                lineWidth = childWidth;
                lineHeight = childHeight;
            }else{
                lineHeight = Math.max(lineHeight,childHeight);
                lineWidth += childWidth;
            }
            int lc = left + params.leftMargin;
            int tc = top + params.topMargin;
            int rc = lc + child.getMeasuredWidth();
            int bc = top + child.getMeasuredHeight();
            child.layout(lc,tc,rc,bc);
            left += childWidth;
        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
