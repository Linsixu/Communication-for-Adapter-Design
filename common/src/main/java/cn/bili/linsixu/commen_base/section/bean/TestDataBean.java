package cn.bili.linsixu.commen_base.section.bean;

import java.util.List;

/**
 * Created by Magic
 * on 2019-06-03.
 * email: linsixu@bilibili.com
 */
public class TestDataBean {
    /**
     * code : 0
     * data : {"trackid":"9749521250531181069","total":100,"items":[{"title":"哔哩哔哩","item":[{"title":"哔哩哔哩主标题","cover":"http://i2.hdslb.com/video/ad/ad9cbaf55a2138e71d6c782ee4ae5461.jpg","author":"bilibili官方","param":"3110165","goto":"av","uri":"bilibili://video/3110165","play":480817,"danmaku":6758,"reply":1111,"like":2222,"desc":"自制 灵感来自于网络上的saber李云龙系列 有兴趣的可以百度一下。视频中的属性设定都是瞎写的 什么A啊B啊C啊什么的看看就好。。","duration_int":158},{"title":"哔哩哔哩demo视频卡","cover":"http://i2.hdslb.com/video/ad/ad9cbaf55a2578e71d6c782ee4ae1249.jpg","author":"bilibili官方","param":"123456","goto":"av","uri":"bilibili://video/123456","play":535,"danmaku":1664,"reply":3525,"like":223636,"desc":"随便写点什么作为简介吧...","duration_int":12533}]},{"title":"血小板","item":[{"title":"工作细胞","cover":"http://i2.hdslb.com/video/ad/ad9cbaf55a2138e71d6c782ee4ae5461.jpg","param":"3110165","goto":"pgc","uri":"http://www.bilibili.com/bangumi/play/ss3110165","badge":"番剧","started":1,"finish":0,"play":480817,"attentions":6758,"rating":8.8,"rating_count":3265154,"media_type":1,"label":"更新至第7话"}]}]}
     * message : ok
     */

    public int code;
    public DataBean data;
    public String message;

    public static class DataBean {
        /**
         * trackid : 9749521250531181069
         * total : 100
         * items : [{"title":"哔哩哔哩","item":[{"title":"哔哩哔哩主标题","cover":"http://i2.hdslb.com/video/ad/ad9cbaf55a2138e71d6c782ee4ae5461.jpg","author":"bilibili官方","param":"3110165","goto":"av","uri":"bilibili://video/3110165","play":480817,"danmaku":6758,"reply":1111,"like":2222,"desc":"自制 灵感来自于网络上的saber李云龙系列 有兴趣的可以百度一下。视频中的属性设定都是瞎写的 什么A啊B啊C啊什么的看看就好。。","duration_int":158},{"title":"哔哩哔哩demo视频卡","cover":"http://i2.hdslb.com/video/ad/ad9cbaf55a2578e71d6c782ee4ae1249.jpg","author":"bilibili官方","param":"123456","goto":"av","uri":"bilibili://video/123456","play":535,"danmaku":1664,"reply":3525,"like":223636,"desc":"随便写点什么作为简介吧...","duration_int":12533}]},{"title":"血小板","item":[{"title":"工作细胞","cover":"http://i2.hdslb.com/video/ad/ad9cbaf55a2138e71d6c782ee4ae5461.jpg","param":"3110165","goto":"pgc","uri":"http://www.bilibili.com/bangumi/play/ss3110165","badge":"番剧","started":1,"finish":0,"play":480817,"attentions":6758,"rating":8.8,"rating_count":3265154,"media_type":1,"label":"更新至第7话"}]}]
         */

        public String trackid;
        public int total;
        public List<ItemsBean> items;

        public static class ItemsBean {
            /**
             * title : 哔哩哔哩
             * item : [{"title":"哔哩哔哩主标题","cover":"http://i2.hdslb.com/video/ad/ad9cbaf55a2138e71d6c782ee4ae5461.jpg","author":"bilibili官方","param":"3110165","goto":"av","uri":"bilibili://video/3110165","play":480817,"danmaku":6758,"reply":1111,"like":2222,"desc":"自制 灵感来自于网络上的saber李云龙系列 有兴趣的可以百度一下。视频中的属性设定都是瞎写的 什么A啊B啊C啊什么的看看就好。。","duration_int":158},{"title":"哔哩哔哩demo视频卡","cover":"http://i2.hdslb.com/video/ad/ad9cbaf55a2578e71d6c782ee4ae1249.jpg","author":"bilibili官方","param":"123456","goto":"av","uri":"bilibili://video/123456","play":535,"danmaku":1664,"reply":3525,"like":223636,"desc":"随便写点什么作为简介吧...","duration_int":12533}]
             */

            public String title;
            public List<ItemBean> item;

            public static class ItemBean {
                /**
                 * title : 哔哩哔哩主标题
                 * cover : http://i2.hdslb.com/video/ad/ad9cbaf55a2138e71d6c782ee4ae5461.jpg
                 * author : bilibili官方
                 * param : 3110165
                 * goto : av
                 * uri : bilibili://video/3110165
                 * play : 480817
                 * danmaku : 6758
                 * reply : 1111
                 * like : 2222
                 * desc : 自制 灵感来自于网络上的saber李云龙系列 有兴趣的可以百度一下。视频中的属性设定都是瞎写的 什么A啊B啊C啊什么的看看就好。。
                 * duration_int : 158
                 */

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
            }
        }
    }
}
