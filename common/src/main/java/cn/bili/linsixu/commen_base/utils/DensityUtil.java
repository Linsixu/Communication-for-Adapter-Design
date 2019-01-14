/*
 * bilibili:android studio write this method in 19-1-14 下午4:43
 */

package cn.bili.linsixu.commen_base.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DensityUtil {
    private static int sWidthPixels;
    private static int sHeightPixels;

//    public static int dp2px(float dpValue) {
//        Context context= Applications.getCurrent();
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
//    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机分辨率获取设备的宽度
     *
     * @param context
     * @return
     */
    public static int getDevicesWidthPixels(Context context) {
        if (sWidthPixels <= 0) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return sWidthPixels = displayMetrics.widthPixels;
        }
        return sWidthPixels;
    }

    /**
     * 根据手机分辨率获取设备的高度
     *
     * @param context
     * @return
     */
    public static int getDevicesHeightPixels(Context context) {
        if (sHeightPixels <= 0) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return sHeightPixels = displayMetrics.heightPixels;
        }
        return sHeightPixels;
    }

    /**
     * 根据手机分辨率获取设备的密度(每英寸占所的像素数)
     *
     * @param context
     * @return
     */
    public static int getDensityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * 下发数据为320ppi下px数据
     *
     * @param context
     * @return
     */
    public static int getDensityPx(Context context, int px_320) {
        return (int) (context.getResources().getDisplayMetrics().densityDpi / 320.0f * px_320);
    }

}