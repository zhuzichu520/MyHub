package com.zhuzichu.android.shared.entity.param

import com.google.gson.annotations.SerializedName
import com.zhuzichu.android.shared.BuildConfig

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/16 1:17 PM
 * since: v 1.0.0
 */
data class ParamAuthor(
    val basicToken: String? = null,
    val authorizations: Authorizations = Authorizations()
) {

    data class Authorizations(
        val scopes: List<String> = listOf("user", "repo", "gist", "notifications"),
        val note: String = "com.zhuzichu.android.myhub",
        val noteUrl: String = BuildConfig.REDIRECT_URL,
        @SerializedName("client_id") val clientId: String = BuildConfig.GITHUB_CLIENT_ID,
        @SerializedName("client_secret") val clientSecret: String = BuildConfig.GITHUB_SECRET
    )
}