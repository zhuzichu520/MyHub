package com.zhuzichu.android.search

import com.alibaba.android.arouter.facade.annotation.Route
import com.zhuzichu.android.shared.base.ActivityBase
import com.zhuzichu.android.shared.route.RoutePath

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/9 11:41 AM
 * since: v 1.0.0
 */
@Route(path = RoutePath.Search.ACTIVITY_SEARCH_MAIN)
class ActivitySearch : ActivityBase() {
    override fun getRoute(): String = RoutePath.Search.FRAGMENT_SEARCH_MAIN
}