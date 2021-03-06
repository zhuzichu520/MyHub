package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.db.daoTrace
import com.zhuzichu.android.shared.entity.data.DataTrace
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.ext.createFlowable
import com.zhuzichu.android.shared.ext.onNextComplete
import io.reactivex.rxjava3.core.Flowable
import java.util.*

/**
 * desc 更新足迹
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseUpdateTrace : UseCase<DataTrace, Flowable<LongArray>>() {

    override fun execute(parameters: DataTrace): Flowable<LongArray> {
        return createFlowable<LongArray> {
            val list = when (parameters.type) {
                DataTrace.TYPE_REPOSITORY -> {
                    daoTrace().selectTraceByRepositoryId(parameters.repositoryId)
                }
                DataTrace.TYPE_USER -> {
                    daoTrace().selectTraceByOwnerId(parameters.ownerId)
                }
                else -> {
                    null
                }
            }
            if (list.isNullOrEmpty()) {
                onNextComplete(daoTrace().addTrace(parameters))
            } else {
                val trace = list.first()
                onNextComplete(daoTrace().addTrace(parameters.copy(id = trace.id, date = Calendar.getInstance())))
            }
        }.bindToSchedulers()
    }

}