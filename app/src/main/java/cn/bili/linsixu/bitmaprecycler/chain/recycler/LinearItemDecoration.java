package cn.bili.linsixu.bitmaprecycler.chain.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.bili.linsixu.bitmaprecycler.R;

/**
 * Created by Magic
 * on 2018/11/2.
 */
public class LinearItemDecoration extends ItemDecoration {

    private Paint mPaint;
    private final int paddingLeft = 200;
    private Bitmap bitmap;

    public LinearItemDecoration(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 2;
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.xunzhang,options);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        for(int i = 0;i < childCount;i++){
            View child = parent.getChildAt(i);
            int top = child.getTop();
            c.drawCircle(paddingLeft / 2,top+child.getHeight()/2,20,mPaint);
            c.drawLine(paddingLeft/2,top,paddingLeft/2,top + child.getHeight(),mPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        int childCount = parent.getChildCount();
        int txtHeight = 0;
        for(int i = 0; i < childCount;i++){
            View child = parent.getChildAt(i);
            if(child instanceof ViewGroup){
                View v = ((ViewGroup) child).getChildAt(i);
                if(v instanceof TextView){
                    Paint.FontMetrics matrix = ((TextView)v).getPaint().getFontMetrics();
                    float baseLine = - matrix.ascent;
                    float endLine = matrix.bottom;
                    txtHeight = (int)((baseLine + endLine)/2);
                }
            }
            int top = child.getTop() + txtHeight;
            int left = manager.getLeftDecorationWidth(child);
            int index = parent.getChildAdapterPosition(child);
            int drawWidth = bitmap.getWidth()/2;
            if(index % 5 == 0){
                c.drawBitmap(bitmap,left-drawWidth,top,mPaint);
            }
        }

        //画蒙版
        mPaint = new Paint();
        View temp = parent.getChildAt(0);
        LinearGradient gradient = new LinearGradient(parent.getWidth() / 2, 0, parent.getWidth() / 2, temp.getHeight() * 3,
                0xff0000ff, 0x000000ff, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
        c.drawRect(0, 0, parent.getWidth(), temp.getHeight() * 3, mPaint);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = paddingLeft;
        outRect.top = 1;
    }
}
