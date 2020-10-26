package com.zhuzichu.android.shared.entity.arg

import com.hiwitech.android.mvvm.base.BaseArg
import com.zhuzichu.android.shared.entity.enumeration.EnumMainType
import kotlinx.android.parcel.Parcelize

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/28 3:30 PM
 * since: v 1.0.0
 */
@Parcelize
class ArgSearch(
    val keyword: String
) : BaseArg()