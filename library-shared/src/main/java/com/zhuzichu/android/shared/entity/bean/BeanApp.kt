package com.zhuzichu.android.shared.entity.bean

import com.google.gson.annotations.SerializedName

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/16 1:15 PM
 * since: v 1.0.0
 */
data class BeanApp(
    @SerializedName("client_id")
    var clientId: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    var url: String? = null


) {
    override fun toString(): String {
        return "BeanApp(clientId=$clientId, name=$name, url=$url)"
    }
}