package com.zhuzichu.android.shared.entity.arg

import com.hiwitech.android.mvvm.base.BaseArg
import kotlinx.android.parcel.Parcelize

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/28 3:30 PM
 * since: v 1.0.0
 */
@Parcelize
class ArgWeb(
    val url: String,
    val title: String? = null,
    val isReadme: Boolean = false,
    val login: String? = null,
    val name: String? = null,
    val needDispatchSafeAreaInset: Boolean? = null
) : BaseArg()