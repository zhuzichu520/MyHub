package com.zhuzichu.android.shared.entity.bean

import com.google.gson.annotations.SerializedName

data class BeanListRes<T : Any>(
    @SerializedName("total_count")
    var totalCount: Int? = null,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean? = null,
    @SerializedName("items")
    var items: List<T>? = null
)