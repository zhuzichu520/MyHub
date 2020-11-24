package com.zhuzichu.android.shared

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.multidex.MultiDex
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger
import com.alibaba.android.arouter.launcher.ARouter
import com.hiwitech.android.mvvm.Mvvm
import com.hiwitech.android.widget.crash.CrashConfig
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import com.umeng.commonsdk.UMConfigure
import com.zhuzichu.android.shared.global.AppGlobal
import com.zhuzichu.android.shared.global.CacheGlobal
import com.zhuzichu.android.shared.rxhttp.ResponseHeaderInterceptor
import com.zhuzichu.android.shared.rxhttp.RxHttpManager
import com.zhuzichu.android.shared.skin.SkinManager
import jonathanfinerty.once.Once
import okhttp3.Cache
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.io.File

class ApplicationHub : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        AppGlobal.init(this)
        //初始化Once
        Once.initialise(this)
        //初始化Http
        RxHttpManager(this)
        CrashConfig.Builder.create().apply()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        //设置loading布局样式
        Mvvm.loadingLayoutId = R.layout.layout_hub_loading
        QMUISwipeBackActivityManager.init(this)
        //初始化路由
        ARouter.init(this)
        //初始化皮肤
        SkinManager.install(this)
        //初始化友盟
        UMConfigure.init(
            this,
            BuildConfig.UMSDK_APPKEY,
            BuildConfig.UMSDK_CHANNEL,
            UMConfigure.DEVICE_TYPE_PHONE,
            null
        )
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /**
     * 初始化ImageLoader
     */
    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this)
        .availableMemoryPercentage(0.25)
        .okHttpClient {
            val cache = Cache(File(CacheGlobal.getCoilCacheDir()), Long.MAX_VALUE)
            val cacheControlInterceptor =
                ResponseHeaderInterceptor("Cache-Control", "max-age=31536000,public")
            val dispatcher = Dispatcher().apply { maxRequestsPerHost = maxRequests }
            OkHttpClient.Builder()
                .cache(cache)
                .dispatcher(dispatcher)
                .addNetworkInterceptor(cacheControlInterceptor)
                .build()
        }
        .apply {
            if (BuildConfig.DEBUG) {
                logger(DebugLogger(Log.VERBOSE))
            }
        }
        .build()

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        SkinManager.applyConfigurationChanged(newConfig)
    }

}
