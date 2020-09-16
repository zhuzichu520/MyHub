package com.zhuzichu.android.shared.domain.login

import com.hiwitech.android.libs.tool.object2Json
import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.bean.BeanAuthor
import com.zhuzichu.android.shared.entity.param.ParamAuthor
import com.zhuzichu.android.shared.ext.bindToSchedulers
import io.reactivex.rxjava3.core.Observable
import rxhttp.RxHttp

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseAuthorizations constructor(
) : UseCase<ParamAuthor, Observable<BeanAuthor>>() {

    override fun execute(parameters: ParamAuthor): Observable<BeanAuthor> {
        return RxHttp.postJson("/authorizations")
            .addHeader("Accept: application/json")
            .addHeader("Authorization", parameters.basicToken)
            .addAll(object2Json(parameters.authorizations))
            .asClass(BeanAuthor::class.java)
            .bindToSchedulers()
    }


}