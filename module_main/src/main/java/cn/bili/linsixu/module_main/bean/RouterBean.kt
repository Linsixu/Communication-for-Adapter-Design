package cn.bili.linsixu.module_main.bean

import org.jetbrains.annotations.NotNull

/**
 * Created by Magic
 * on 2019/4/9.
 * email: linsixu@bilibili.com
 */
class RouterBean {
    @NotNull
    lateinit var text: String
    @NotNull
    lateinit var routerName: String

    constructor(text: String, routerName: String) {
        this.text = text
        this.routerName = routerName
    }
}