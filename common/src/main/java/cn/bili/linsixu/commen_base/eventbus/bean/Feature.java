/*
 * bilibili:android studio write this method in 19-1-14 下午4:39
 */

package cn.bili.linsixu.commen_base.eventbus.bean;

import java.io.Serializable;

public class Feature implements Serializable {
    private Class mClass;
    private String mName;
    private boolean mEnabled;

    public Feature(String mName, Class mClass) {
        this(mName,mClass,true);
    }

    public Feature(String mName, Class mClass, boolean mEnabled) {
        this.mClass = mClass;
        this.mName = mName;
        this.mEnabled = mEnabled;
    }

    public boolean ismEnabled() {
        return mEnabled;
    }

    public void setmEnabled(boolean mEnabled) {
        this.mEnabled = mEnabled;
    }

    public Class getmClass() {
        return mClass;
    }

    public String getmName() {
        return mName;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Feature feature = (Feature) obj;
        return mName.equals(feature.mName);
    }

    @Override
    public int hashCode() {
        return mName.hashCode();
    }
}
