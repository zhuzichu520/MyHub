package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.db.daoHistory
import com.zhuzichu.android.shared.db.daoTrace
import com.zhuzichu.android.shared.entity.data.DataSearchHistory
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.ext.createFlowable
import com.zhuzichu.android.shared.ext.onNextComplete
import io.reactivex.rxjava3.core.Flowable

/**
 * desc 删除搜索足迹
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseDeleteAllTrace : UseCase<Unit, Flowable<Unit>>() {

    override fun execute(parameters: Unit): Flowable<Unit> {
        return createFlowable<Unit> {
            daoTrace().deleteAll()
            onNextComplete(Unit)
        }.bindToSchedulers()
    }

}