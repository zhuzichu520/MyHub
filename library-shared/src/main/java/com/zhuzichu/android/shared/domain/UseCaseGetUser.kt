package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.bean.BeanUser
import com.zhuzichu.android.shared.ext.bindToSchedulers
import io.reactivex.rxjava3.core.Observable
import rxhttp.RxHttp

/**
 * desc 获取用户信息
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseGetUser : UseCase<String, Observable<BeanUser>>() {

    override fun execute(parameters: String): Observable<BeanUser> {
        return RxHttp.get("/users/%s", parameters)
            .asClass(BeanUser::class.java)
            .bindToSchedulers()
    }

}