package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.bean.BeanOwner
import com.zhuzichu.android.shared.entity.bean.BeanRepository
import com.zhuzichu.android.shared.entity.param.ParamSearchRepositories
import com.zhuzichu.android.shared.entity.param.ParamSearchUsers
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.http.entity.PageList
import io.reactivex.rxjava3.core.Observable
import rxhttp.RxHttp


class UseCaseSearchUsers : UseCase<ParamSearchUsers, Observable<PageList<BeanOwner>>>() {

    override fun execute(parameters: ParamSearchUsers): Observable<PageList<BeanOwner>> {
        return RxHttp.get("/search/users")
            .add("q", parameters.query)
            .add("sort", parameters.sort)
            .add("order", parameters.order)
            .add("page", parameters.page)
            .add("per_page", parameters.pageSize)
            .asResponsePageList(BeanOwner::class.java)
            .bindToSchedulers()
    }
}