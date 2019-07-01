package cn.bili.linsixu.module_main.bean;

import java.util.List;

/**
 * Created by Magic
 * on 2019-07-01.
 * email: linsixu@bilibili.com
 */
public class LiveDataBean {

    public int code;
    public DataBean data;
    public String message;

    public static class DataBean {

        public String trackid;
        public int total;
        public List<ItemsBean> items;
        
        public static class ItemsBean {
            public String title;
            public List<ItemBean> item;

            public static class ItemBean {

                public String title;
                public String cover;
                public String author;
                public String param;
                public String gotoX;
                public String uri;
                public int play;
                public int danmaku;
                public int reply;
                public int like;
                public String desc;
                public int duration_int;

                @Override
                public String toString() {
                    return "ItemBean{" +
                            "title='" + title + '\'' +
                            ", cover='" + cover + '\'' +
                            ", author='" + author + '\'' +
                            ", param='" + param + '\'' +
                            ", gotoX='" + gotoX + '\'' +
                            ", uri='" + uri + '\'' +
                            ", play=" + play +
                            ", danmaku=" + danmaku +
                            ", reply=" + reply +
                            ", like=" + like +
                            ", desc='" + desc + '\'' +
                            ", duration_int=" + duration_int +
                            '}';
                }
            }
        }
    }
}
