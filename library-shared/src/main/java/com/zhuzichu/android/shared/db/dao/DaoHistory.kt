package com.zhuzichu.android.shared.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhuzichu.android.shared.entity.data.DataSearchHistory

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/28 2:48 PM
 * since: v 1.0.0
 */

@Dao
interface DaoHistory {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSearchHistory(vararg datas: DataSearchHistory): LongArray

    @Query("SELECT * FROM DataSearchHistory ORDER BY date DESC")
    fun selectList(): List<DataSearchHistory>

    @Query("SELECT * FROM DataSearchHistory WHERE name=:name")
    fun selectSearchHistoryByName(name: String?): List<DataSearchHistory>

}