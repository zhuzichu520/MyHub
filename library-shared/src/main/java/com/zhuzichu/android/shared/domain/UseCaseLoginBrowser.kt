package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.BuildConfig
import com.zhuzichu.android.shared.entity.param.ParamAuthor
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.ext.createFlowable
import com.zhuzichu.android.shared.ext.onNextComplete
import io.reactivex.rxjava3.core.Flowable
import java.util.*

/**
 * desc 授权登录
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseLoginBrowser : UseCase<Unit, Flowable<String>>() {

    override fun execute(parameters: Unit): Flowable<String> {
        return createFlowable<String> {
            val oAuth2Url =
                "${BuildConfig.HOST_HTML}/login/oauth/authorize?" +
                        "client_id=${BuildConfig.GITHUB_CLIENT_ID}" +
                        "&scope=user,repo,gist,notifications" +
                        "&state=${UUID.randomUUID()}"
            onNextComplete(oAuth2Url)
        }.bindToSchedulers()
    }

}