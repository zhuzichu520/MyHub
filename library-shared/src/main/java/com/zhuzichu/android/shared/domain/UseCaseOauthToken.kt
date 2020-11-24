package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.BuildConfig
import com.zhuzichu.android.shared.entity.bean.BeanOauthToken
import com.zhuzichu.android.shared.entity.param.ParamOauthToken
import com.zhuzichu.android.shared.ext.bindToSchedulers
import io.reactivex.rxjava3.core.Observable
import rxhttp.RxHttp

/**
 * desc 授权登录
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseOauthToken : UseCase<ParamOauthToken, Observable<BeanOauthToken>>() {

    override fun execute(parameters: ParamOauthToken): Observable<BeanOauthToken> {
        return RxHttp.postJson("/login/oauth/access_token")
            .setDomainToHtmlIfAbsent()
            .addHeader("Accept: application/json")
            .add("client_id", BuildConfig.GITHUB_CLIENT_ID)
            .add("client_secret", BuildConfig.GITHUB_SECRET)
            .add("code", parameters.code)
            .add("state", parameters.state)
            .asClass(BeanOauthToken::class.java)
            .bindToSchedulers()
    }

}