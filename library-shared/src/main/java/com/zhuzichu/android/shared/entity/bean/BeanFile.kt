package com.zhuzichu.android.shared.entity.bean

import com.google.gson.annotations.SerializedName
import com.zhuzichu.android.shared.entity.enumeration.EnumFileType


/**
 * desc
 * author: 朱子楚
 * time: 2020/11/2 4:29 PM
 * since: v 1.0.0
 */
data class BeanFile(
    @SerializedName("download_url")
    var downloadUrl: String? = null,
    @SerializedName("git_url")
    var gitUrl: String? = null,
    @SerializedName("html_url")
    var htmlUrl: String? = null,
    @SerializedName("_links")
    var links: BeanLinks? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("path")
    var path: String? = null,
    @SerializedName("sha")
    var sha: String? = null,
    @SerializedName("size")
    var size: Int? = null,
    @SerializedName("type")
    var type: EnumFileType? = null,
    @SerializedName("url")
    var url: String? = null
)