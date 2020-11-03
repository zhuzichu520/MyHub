package com.zhuzichu.android.shared.entity.param

import com.zhuzichu.android.shared.entity.enumeration.EnumFileAccept

data class ParamFile(
    val url: String,
    val fileAccept: EnumFileAccept
)