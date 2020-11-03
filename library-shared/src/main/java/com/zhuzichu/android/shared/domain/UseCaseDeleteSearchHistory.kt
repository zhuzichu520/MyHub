package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.db.daoHistory
import com.zhuzichu.android.shared.entity.data.DataSearchHistory
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.ext.createFlowable
import com.zhuzichu.android.shared.ext.onNextComplete
import io.reactivex.rxjava3.core.Flowable

/**
 * desc 删除搜索历史
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseDeleteSearchHistory : UseCase<List<DataSearchHistory>, Flowable<Unit>>() {

    override fun execute(parameters: List<DataSearchHistory>): Flowable<Unit> {
        return createFlowable<Unit> {
            daoHistory().deleteSearchHitory(parameters)
            onNextComplete(Unit)
        }.bindToSchedulers()
    }

}