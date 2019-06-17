package cn.bili.linsixu.commen_base.section.parse

import android.content.Context
import cn.bili.linsixu.commen_base.parse.BaseApiParser
import cn.bili.linsixu.commen_base.section.bean.TestDataBean
import com.alibaba.fastjson.JSON

/**
 * Created by Magic
 * on 2019-06-03.
 * email: linsixu@bilibili.com
 */
class TestDataParser(context: Context, jsonFile: String) : BaseApiParser(context, jsonFile) {
    fun getResponseFromLocal(): TestDataBean.DataBean {
        val jsonObject = JSON.parseObject(getJson())
        val feedCard = TestDataBean()

        feedCard.code = jsonObject.getIntValue("code")
        feedCard.message = jsonObject.getString("message")
        if (jsonObject.containsKey("data")) {
            val result = jsonObject.getJSONObject("data").toJSONString()
            val t = JSON.parseObject(result, TestDataBean.DataBean::class.java)
            feedCard.data = t
        }

        return feedCard.data
    }
}