package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.bean.BeanUser
import com.zhuzichu.android.shared.ext.bindToSchedulers
import io.reactivex.rxjava3.core.Observable
import rxhttp.RxHttp

/**
 * desc 授权登录
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseGetUser : UseCase<Unit, Observable<BeanUser>>() {

    override fun execute(parameters: Unit): Observable<BeanUser> {
        return RxHttp.postJson("/user")
            .addHeader("Accept: application/json")
            .asClass(BeanUser::class.java)
            .bindToSchedulers()
    }

}