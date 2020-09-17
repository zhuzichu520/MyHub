package com.zhuzichu.android.me

import com.alibaba.android.arouter.facade.annotation.Route
import com.zhuzichu.android.shared.base.ActivityBase
import com.zhuzichu.android.shared.route.RoutePath

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/9 11:41 AM
 * since: v 1.0.0
 */
@Route(path = RoutePath.Me.ACTIVITY_ME_MAIN)
class ActivityMe : ActivityBase() {
    override fun getRoute(): String = RoutePath.Me.FRAGMENT_ME_MAIN
}