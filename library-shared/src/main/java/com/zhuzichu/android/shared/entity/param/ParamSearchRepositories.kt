package com.zhuzichu.android.shared.entity.param

data class ParamSearchRepositories(
    val query: String,
    val page: Int,
    val pageSize: Int,
    val sort: String = SORT_BAST_MATCH,
    val order: String = ORDER_DESC
) {
    companion object {

        const val ORDER_DESC = "desc"
        const val ORDER_ASC = "asc"

        //最匹配的
        const val SORT_BAST_MATCH = ""

        //星标
        const val SORT_STARS = "stars"

        //分支
        const val SORT_FORKS = "forks"

        //更新
        const val SORT_UPDATED = "updated"
    }
}