package com.zhuzichu.android.myhub

import com.zhuzichu.android.shared.base.ActivityBase
import com.zhuzichu.android.shared.route.RoutePath

class ActivityMain : ActivityBase() {
    override fun getRoute(): String = RoutePath.Login.FRAGMENT_LOGIN_MAIN
}