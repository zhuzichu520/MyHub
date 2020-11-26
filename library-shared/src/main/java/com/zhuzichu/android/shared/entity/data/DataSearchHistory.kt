package com.zhuzichu.android.shared.entity.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/26 3:44 PM
 * since: v 1.0.0
 */
@Entity
data class DataSearchHistory(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var name: String? = null,
    var date: Calendar = Calendar.getInstance()
) : Serializable