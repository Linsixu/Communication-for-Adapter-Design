package cn.bili.linsixu.module_main.parser

import android.content.Context
import cn.bili.linsixu.commen_base.parse.BaseApiParser
import cn.bili.linsixu.commen_base.section.bean.TestDataBean
import cn.bili.linsixu.module_main.bean.LiveDataBean
import com.alibaba.fastjson.JSON

/**
 * Created by Magic
 * on 2019-06-03.
 * email: linsixu@bilibili.com
 */
class LiveDataParser(context: Context, jsonFile: String) : BaseApiParser(context, jsonFile) {
    fun getResponseFromLocal(): LiveDataBean {
        val jsonObject = JSON.parseObject(getJson())
        val feedCard = LiveDataBean()

        feedCard.code = jsonObject.getIntValue("code")
        feedCard.message = jsonObject.getString("message")
        if (jsonObject.containsKey("data")) {
            val result = jsonObject.getJSONObject("data").toJSONString()
            val t = JSON.parseObject(result, LiveDataBean.DataBean::class.java)
            feedCard.data = t
        }

        return feedCard
    }
}