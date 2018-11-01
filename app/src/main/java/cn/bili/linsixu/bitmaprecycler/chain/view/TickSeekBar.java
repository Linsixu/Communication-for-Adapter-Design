package cn.bili.linsixu.bitmaprecycler.chain.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import cn.bili.linsixu.bitmaprecycler.R;

/**
 * Created by Magic
 * on 2018/11/1.
 */
public class TickSeekBar extends AppCompatSeekBar {
    private Context context;
    private final int tick = 5;
    private int[] mBaseX = new int[tick+1];
    private final int tickWidth = 54,tickHeight = 54;
    public TickSeekBar(Context context) {
        super(context);
        this.context = context;
    }

    public TickSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public TickSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int drawWidth = getWidth() - paddingLeft - paddingRight;
        final int height = getHeight();
        Drawable drawable = new ColorDrawable(context.getResources().getColor(R.color.colorPrimary));

        Drawable drawable2 = context.getResources().getDrawable(R.drawable.fish);
//        Resources re = context.getResources();
//        Bitmap bmp = BitmapFactory.decodeResource(re,R.drawable.fish);
//        BitmapDrawable drawable2 = new BitmapDrawable(bmp);
        int fishWidth = drawable2.getIntrinsicWidth();
        int fishHeight = drawable2.getIntrinsicHeight();

        for(int i = 0; i <= tick;i++){
            mBaseX[i] = paddingLeft + i * drawWidth / tick;
            drawable.setBounds(mBaseX[i],(height / 2 - 32),mBaseX[i]+4,(height/2));
            drawable.draw(canvas);
            int baseX = mBaseX[i];
            if(i == tick){
                baseX -= fishWidth;
            }else if(i != 0){
                baseX -= fishWidth;
            }
            drawable2.setBounds(baseX,0,baseX+fishWidth,fishHeight);
            drawable2.draw(canvas);
        }

    }

}
