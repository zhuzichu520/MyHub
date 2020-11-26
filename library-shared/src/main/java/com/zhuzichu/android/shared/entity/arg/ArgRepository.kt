package com.zhuzichu.android.shared.entity.arg

import com.hiwitech.android.mvvm.base.BaseArg
import com.zhuzichu.android.shared.entity.bean.BeanRepository
import com.zhuzichu.android.shared.entity.data.DataTrace

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/28 3:30 PM
 * since: v 1.0.0
 */
class ArgRepository(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var fullName: String? = null,
    var language: String? = null,
    var size: Int? = null,
    var openIssuesCount: Int? = null,
    var stargazersCount: Int? = null,
    var forksCount: Int? = null,
    var watchers: Int? = null,
    var login: String? = null
) : BaseArg() {
    companion object {

        fun beanToArg(bean: BeanRepository): ArgRepository {
            return ArgRepository(
                bean.id,
                bean.name,
                bean.description,
                bean.fullName,
                bean.language,
                bean.size,
                bean.openIssuesCount,
                bean.stargazersCount,
                bean.forksCount,
                bean.watchers,
                bean.owner?.login
            )
        }

        fun dataToArg(data: DataTrace): ArgRepository {
            return ArgRepository(
                data.id,
                data.name,
                data.description,
                data.fullName,
                data.language,
                data.size,
                data.openIssuesCount,
                data.stargazersCount,
                data.forksCount,
                data.watchers,
                data.login
            )
        }

    }
}