package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.db.daoTrace
import com.zhuzichu.android.shared.entity.bean.BeanUser
import com.zhuzichu.android.shared.entity.data.DataTrace
import com.zhuzichu.android.shared.entity.param.ParamPager
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.ext.createFlowable
import com.zhuzichu.android.shared.ext.onNextComplete
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import rxhttp.RxHttp

/**
 * desc 获取用足迹
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseGetTrace : UseCase<ParamPager, Flowable<List<DataTrace>>>() {

    override fun execute(parameters: ParamPager): Flowable<List<DataTrace>> {
        return createFlowable<List<DataTrace>> {
            onNextComplete(
                daoTrace().selectList(parameters.pageSize, (parameters.page - 1) * parameters.pageSize)
            )
        }.bindToSchedulers()
    }

}