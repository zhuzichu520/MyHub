package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.param.ParamFile
import com.zhuzichu.android.shared.ext.bindToSchedulers
import io.reactivex.rxjava3.core.Observable
import rxhttp.RxHttp

/**
 * desc 获取文件资源
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseGetRepoFile : UseCase<ParamFile, Observable<String>>() {

    override fun execute(parameters: ParamFile): Observable<String> {
        return RxHttp
            .get(parameters.url)
            .addHeader(parameters.fileAccept.accept)
            .asString()
            .bindToSchedulers()
    }

}