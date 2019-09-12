package cn.bili.linsixu.commen_base.view.span;

import android.support.annotation.Keep;

import java.util.List;

/**
 * Created by Magic
 * on 2019-07-30.
 * email: linsixu@bilibili.com
 */
@Keep
public class LikeBean {

    /**
     * like_info : {"display_text":"赞了","like_users":[{"uid":23333,"uname":"123"},{"uid":23333,"uname":"123"}]}
     */

    public LikeInfoBean like_info;


    public static class LikeInfoBean {
        /**
         * display_text : 赞了
         * like_users : [{"uid":23333,"uname":"123"},{"uid":23333,"uname":"123"}]
         */

        public String display_text;
        public List<LikeUsersBean> like_users;

        public static class LikeUsersBean {
            /**
             * uid : 23333
             * uname : 123
             */

            public int uid;
            public String uname;
            public String url;
        }
    }
}
