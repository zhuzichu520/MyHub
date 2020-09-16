package com.zhuzichu.android.login.entity

import com.google.gson.annotations.SerializedName
import com.zhuzichu.android.shared.BuildConfig

data class ParamterAuthorizations(
    val scopes: List<String> = listOf("user", "repo", "gist", "notifications"),
    val note: String = "com.zhuzichu.android.myhub",
    val noteUrl: String = BuildConfig.REDIRECT_URL,
    @SerializedName("client_id") val clientId: String = BuildConfig.GITHUB_CLIENT_ID,
    @SerializedName("client_secret") val clientSecret: String = BuildConfig.GITHUB_SECRET
)