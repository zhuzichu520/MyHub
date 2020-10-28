package com.zhuzichu.android.shared.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhuzichu.android.shared.entity.data.DataTrace

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/26 4:41 PM
 * since: v 1.0.0
 */

@Dao
interface DaoTrace {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTrace(vararg datas: DataTrace): LongArray

    @Query("SELECT * FROM DataTrace WHERE ownerId=:id")
    fun selectTraceByOwnerId(id: Long?): List<DataTrace>

    @Query("SELECT * FROM DataTrace WHERE repositoryId=:id")
    fun selectTraceByRepositoryId(id: Long?): List<DataTrace>

    @Query("SELECT * FROM DataTrace ORDER BY date DESC LIMIT :limit OFFSET :offset")
    fun selectList(limit:Int,offset:Int):  List<DataTrace>

}