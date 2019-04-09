/*
 * Copyright (C) 2018 Bilibili
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.bili.linsixu.commen_base.feed.card

import java.util.*

/**
 * Created by Magic
 * on 2019/3/27.
 * email: linsixu@bilibili.com
 */
open class InterAction constructor(var actionId: Int) {
    var mExtras: MutableMap<String, Any?> = HashMap()

    fun appendExtra(key: String, value: Any?): InterAction {
        mExtras[key] = value
        return this
    }

    fun getExtra(key: String): Any? {
        return mExtras[key]
    }
}
