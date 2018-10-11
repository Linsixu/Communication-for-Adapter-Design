package cn.bili.linsixu.bitmaprecycler.chain.bean;

import java.io.Serializable;

/**
 * Created by Magic
 * on 2018/10/11.
 */
public class Feature implements Serializable {
    private Class mClass;
    private String mName;
    private boolean mEnabled;

    public Feature(String mName,Class mClass) {
        this(mName,mClass,true);
    }

    public Feature(String mName,Class mClass,boolean mEnabled) {
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
