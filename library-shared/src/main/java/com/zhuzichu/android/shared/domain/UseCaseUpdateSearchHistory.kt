package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.db.daoHistory
import com.zhuzichu.android.shared.db.daoTrace
import com.zhuzichu.android.shared.entity.data.DataSearchHistory
import com.zhuzichu.android.shared.entity.data.DataTrace
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.ext.createFlowable
import com.zhuzichu.android.shared.ext.onNextComplete
import io.reactivex.rxjava3.core.Flowable
import java.util.*

/**
 * desc 更新搜索历史
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseUpdateSearchHistory : UseCase<DataSearchHistory, Flowable<LongArray>>() {

    override fun execute(parameters: DataSearchHistory): Flowable<LongArray> {
        return createFlowable<LongArray> {
            val list = daoHistory().selectSearchHistoryByName(parameters.name)
            if (list.isNullOrEmpty()) {
                onNextComplete(daoHistory().addSearchHistory(parameters))
            } else {
                val history = list.first()
                onNextComplete(
                    daoHistory().addSearchHistory(
                        parameters.copy(
                            id = history.id,
                            date = Calendar.getInstance()
                        )
                    )
                )
            }
        }.bindToSchedulers()
    }

}