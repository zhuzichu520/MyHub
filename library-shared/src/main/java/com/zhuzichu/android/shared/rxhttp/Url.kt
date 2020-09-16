package com.zhuzichu.android.shared.rxhttp

import com.zhuzichu.android.shared.BuildConfig
import rxhttp.wrapper.annotation.DefaultDomain

object Url {
    @DefaultDomain
    const val baseUrl = BuildConfig.HOST_APP2
}
