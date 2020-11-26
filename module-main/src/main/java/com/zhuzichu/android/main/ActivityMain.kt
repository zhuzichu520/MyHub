package com.zhuzichu.android.main

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.Mvvm
import com.zhuzichu.android.shared.base.ActivityBase
import com.zhuzichu.android.shared.entity.arg.ArgMain
import com.zhuzichu.android.shared.entity.enumeration.EnumMainType
import com.zhuzichu.android.shared.route.RoutePath
import com.zhuzichu.android.shared.storage.AppStorage

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/9 11:41 AM
 * since: v 1.0.0
 */
@Route(path = RoutePath.Main.ACTIVITY_MAIN_MAIN)
class ActivityMain : ActivityBase() {

    override fun getRoute(): String = RoutePath.Main.FRAGMENT_MAIN_MAIN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val extras = intent?.extras ?: return
        val arg = extras.getSerializable(Mvvm.KEY_ARG) as? ArgMain ?: return
        when (arg.mainType) {
            EnumMainType.LOGOUT -> {
                AppStorage.token = null
                navigate(RoutePath.Login.ACTIVITY_LOGIN_MAIN, isPop = true)
            }
            else -> {

            }
        }
    }

}