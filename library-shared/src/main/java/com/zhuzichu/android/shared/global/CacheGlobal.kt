package com.zhuzichu.android.shared.global

import com.hiwitech.android.libs.tool.isExternalStorageWriteable
import com.zhuzichu.android.shared.global.AppGlobal.context
import java.io.File

object CacheGlobal {

    private const val CACHE_COIL_FILE_NAME = "cache_coil"

    private const val CACHE_MMKV_FILE_NAME = "cache_mmkv"

    private const val CACHE_LOG_FILE_NAME = "cache_log"

    fun initDir() {
        getCoilCacheDir()
        getMmkvCacheDir()
        getLogCacheDir()
    }

    fun getLogCacheDir(): String {
        return getDiskCacheDir(CACHE_LOG_FILE_NAME).absolutePath
    }

    fun getMmkvCacheDir(): String {
        return getDiskCacheDir(CACHE_MMKV_FILE_NAME).absolutePath
    }

    fun getCoilCacheDir(): String {
        return getDiskCacheDir(CACHE_COIL_FILE_NAME).absolutePath
    }

    private fun getBaseDiskCacheDir(): File {
        return if (isExternalStorageWriteable()) {
            context.externalCacheDir!!
        } else {
            context.cacheDir
        }
    }

    private fun getDiskCacheDir(last: String): File {
        val file = File(getBaseDiskCacheDir().toString(), last)
        if (!file.exists()) {
            file.mkdirs()
        }
        return file.absoluteFile
    }

}