package com.zhuzichu.android.shared.entity.bean

import com.google.gson.annotations.SerializedName

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/16 1:15 PM
 * since: v 1.0.0
 */
data class BeanOauthToken(
    @SerializedName("access_token")
    var accessToken: String? = null,
    @SerializedName("scope")
    var scope: String? = null
)
