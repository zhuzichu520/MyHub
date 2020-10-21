package com.zhuzichu.android.shared.entity.bean

import com.google.gson.annotations.SerializedName

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/16 1:15 PM
 * since: v 1.0.0
 */
data class BeanAuthor(
    @SerializedName("app")
    var app: BeanApp? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("fingerprint")
    var fingerprint: Any? = null,
    @SerializedName("hashed_token")
    var hashedToken: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("note")
    var note: String? = null,
    @SerializedName("note_url")
    var noteUrl: Any? = null,
    @SerializedName("scopes")
    var scopes: List<String?>? = null,
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("token_last_eight")
    var tokenLastEight: String? = null,
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @SerializedName("url")
    var url: String? = null
) {
    override fun toString(): String {
        return "BeanAuthor(app=$app, createdAt=$createdAt, fingerprint=$fingerprint, hashedToken=$hashedToken, id=$id, note=$note, noteUrl=$noteUrl, scopes=$scopes, token=$token, tokenLastEight=$tokenLastEight, updatedAt=$updatedAt, url=$url)"
    }
}
