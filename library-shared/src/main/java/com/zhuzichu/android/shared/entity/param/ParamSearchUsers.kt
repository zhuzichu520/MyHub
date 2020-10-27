package com.zhuzichu.android.shared.entity.param

data class ParamSearchUsers(
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

        //跟随者
        const val SORT_FOLLOWERS = "followers"

        //仓库版本
        const val SORT_REPOSITORIES = "repositories"

        //加入的
        const val SORT_JOINED = "joined"
    }
}