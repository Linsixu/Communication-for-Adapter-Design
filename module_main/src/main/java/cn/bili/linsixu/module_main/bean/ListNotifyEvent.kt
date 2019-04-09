package cn.bili.linsixu.module_main.bean

/**
 * Created by Magic
 * on 2019/3/1.
 * email: linsixu@bilibili.com
 */
class ListNotifyEvent {
    var start: Int = 0
    var childCount: Int = 0
    var type: Notify = Notify.INIT
    //start最小值为1
    constructor(start: Int, childCount: Int, type: Notify = Notify.INIT){
        this.start = start
        this.childCount = childCount
        this.type = type
    }

    enum class Notify {
        //对应初始化，addALL，add，remove，set操作
        INIT, ADDALL, ADD, DELETE, UPDATE, INSERT
    }

    override fun toString(): String {
        return "ListNotifyEvent(start=$start, childCount=$childCount, type=$type)"
    }
}