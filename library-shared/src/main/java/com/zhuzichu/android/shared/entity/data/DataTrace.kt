package com.zhuzichu.android.shared.entity.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhuzichu.android.shared.entity.bean.BeanOwner
import com.zhuzichu.android.shared.entity.bean.BeanRepository
import com.zhuzichu.android.shared.entity.bean.BeanUser
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/26 3:44 PM
 * since: v 1.0.0
 */
@Parcelize
@Entity
data class DataTrace(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var repositoryId: Long? = null,
    var ownerId: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var fullName: String? = null,
    var language: String? = null,
    var size: Int? = null,
    var avatarUrl: String? = null,
    var openIssuesCount: Int? = null,
    var stargazersCount: Int? = null,
    var forksCount: Int? = null,
    var watchers: Int? = null,
    var login: String? = null,
    var type: Int? = null,
    var date: Calendar = Calendar.getInstance()
) : Parcelable {
    companion object {

        const val TYPE_REPOSITORY = 0
        const val TYPE_USER = 1

        fun beanToData(bean: BeanRepository): DataTrace {
            return DataTrace(
                null,
                bean.id,
                null,
                bean.name,
                bean.description,
                bean.fullName,
                bean.language,
                bean.size,
                bean.owner?.avatarUrl,
                bean.openIssuesCount,
                bean.stargazersCount,
                bean.forksCount,
                bean.watchers,
                bean.owner?.login,
                TYPE_REPOSITORY
            )
        }

        fun beanToData(bean: BeanOwner): DataTrace {
            return DataTrace(
                null,
                null,
                bean.id,
                null,
                null,
                null,
                null,
                null,
                bean.avatarUrl,
                null,
                null,
                null,
                null,
                bean.login,
                TYPE_USER
            )
        }

    }
}