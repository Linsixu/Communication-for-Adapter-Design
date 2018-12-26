package cn.bili.linsixu.commen_base.utils;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Magic
 * on 2018/12/11.
 */
public class PolicyEvent {
    public static final int EVENT_SOURCE = 0;
    public static final int EVENT_TYPE = 1;
    public static final int EVENT_RESULT = 2;
    private int flag;


    @IntDef({EVENT_SOURCE,EVENT_TYPE,EVENT_RESULT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventName{}

    public PolicyEvent(@EventName int flag) {
        this.flag = flag;
    }
}
