package com.zhuzichu.android.shared.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.db.dao.DaoHistory
import com.zhuzichu.android.shared.db.dao.DaoTrace
import com.zhuzichu.android.shared.entity.data.DataSearchHistory
import com.zhuzichu.android.shared.entity.data.DataTrace
import com.zhuzichu.android.shared.global.AppGlobal.context

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/26 3:24 PM
 * since: v 1.0.0
 */
@Database(
    entities = [
        DataTrace::class,
        DataSearchHistory::class,
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DatabaseHub : RoomDatabase() {

    abstract fun daoTrace(): DaoTrace

    abstract fun daoHistory(): DaoHistory

    companion object {

        private const val DATABASE_NAME = "myhub.db"

        @Volatile
        private var INSTANCE: DatabaseHub? = null

        fun getInstance(): DatabaseHub {
            return (INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }).apply {
                populateInitialData()
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DatabaseHub::class.java, DATABASE_NAME
            ).allowMainThreadQueries().addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {

                }
            }).build()

    }

    /**
     * 初始化数据
     */
    private fun populateInitialData() {

    }

}


fun daoTrace(): DaoTrace {
    return DatabaseHub.getInstance().daoTrace()
}

fun daoHistory(): DaoHistory {
    return DatabaseHub.getInstance().daoHistory()
}