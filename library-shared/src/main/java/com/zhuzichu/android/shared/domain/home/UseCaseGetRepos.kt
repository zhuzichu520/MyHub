package com.zhuzichu.android.shared.domain.home

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.bean.BeanRepository
import com.zhuzichu.android.shared.entity.param.ParamGetRepos
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.http.entity.PageList
import io.reactivex.rxjava3.core.Observable
import rxhttp.RxHttp


class UseCaseGetRepos : UseCase<ParamGetRepos, Observable<PageList<BeanRepository>>>() {

    override fun execute(parameters: ParamGetRepos): Observable<PageList<BeanRepository>> {
        return RxHttp.get("/search/repositories")
            .add("q", parameters.query)
            .add("sort", parameters.sort)
            .add("order", parameters.order)
            .add("page", parameters.page)
            .add("per_page", parameters.pageSize)
            .asResponsePageList(BeanRepository::class.java)
            .bindToSchedulers()
    }
}