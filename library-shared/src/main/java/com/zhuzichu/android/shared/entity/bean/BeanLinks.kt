package com.zhuzichu.android.shared.entity.bean

import com.google.gson.annotations.SerializedName

/**
 * desc
 * author: 朱子楚
 * time: 2020/11/2 4:33 PM
 * since: v 1.0.0
 */
data class BeanLinks(
    @SerializedName("git")
    var git: String? = null,
    @SerializedName("html")
    var html: String? = null,
    @SerializedName("self")
    var self: String? = null
)