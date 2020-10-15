package com.zhuzichu.android.shared.rxhttp

import com.zhuzichu.android.shared.BuildConfig
import rxhttp.wrapper.annotation.DefaultDomain
import rxhttp.wrapper.annotation.Domain

object Url {

    @DefaultDomain
    const val baseUrl = BuildConfig.HOST_APP2

    @Domain(name = "Html")
    const val htmlUrl = BuildConfig.HOST_HTML

}
