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
        Once.initialise(this)
        RxHttpManager(this)
        CrashConfig.Builder.create().apply()
        Mvvm.loadingLayoutId = R.layout.layout_hub_loading
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        // 尽可能早，推荐在Application中初始化
        QMUISwipeBackActivityManager.init(this)
        ARouter.init(this)
        SkinManager.install(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

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
        if (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
            SkinManager.changeSkin(SkinManager.SKIN_DARK)
        } else if (SkinManager.getCurrentSkin() == SkinManager.SKIN_DARK) {
            SkinManager.changeSkin(SkinManager.SKIN_BLUE)
        }
    }

}
