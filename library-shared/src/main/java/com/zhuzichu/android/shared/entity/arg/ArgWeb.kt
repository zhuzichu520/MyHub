package com.zhuzichu.android.shared.entity.arg

import com.hiwitech.android.mvvm.base.BaseArg

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/28 3:30 PM
 * since: v 1.0.0
 */
class ArgWeb(
    val url: String,
    val title: String? = null,
    val needDispatchSafeAreaInset: Boolean? = null
) : BaseArg()