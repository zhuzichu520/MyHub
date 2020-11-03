package com.zhuzichu.android.shared.entity.enumeration

import com.google.gson.annotations.SerializedName

/**
 * desc
 * author: 朱子楚
 * time: 2020/11/2 4:51 PM
 * since: v 1.0.0
 */
enum class EnumFileType {
    @SerializedName("file")
    FILE,

    @SerializedName("dir")
    DIR
}