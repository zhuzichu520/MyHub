package com.zhuzichu.android.shared.entity.param

data class ParamGetRepos(
    val query: String,
    val page: Int,
    val pageSize: Int,
    val sort: String = "stars",
    val order: String = "desc"
)