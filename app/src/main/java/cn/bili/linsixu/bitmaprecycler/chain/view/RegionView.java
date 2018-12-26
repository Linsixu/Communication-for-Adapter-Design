package cn.bili.linsixu.bitmaprecycler.chain.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Magic
 * on 2018/11/3.
 */
public class RegionView extends View {

    public RegionView(Context context) {
        super(context);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect1 = new Rect(100,100,400,200);
        Rect rect2 = new Rect(200,0,300,300);
        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);
        region1.op(region2, Region.Op.INTERSECT);
    }

    private void drawRegion(Canvas canvas, Region region, Paint paint){
        RegionIterator regionIterator = new RegionIterator(region);
        Rect t = new Rect();
        while (regionIterator.next(t)){
            canvas.drawRect(t,new Paint());
        }
    }
}
