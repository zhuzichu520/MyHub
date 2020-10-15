package com.zhuzichu.android.shared.storage

import com.tencent.mmkv.MMKV
import com.zhuzichu.android.shared.skin.SkinManager.Companion.SKIN_BLUE

object AppStorage {

    private const val PREFS_NAME = "app"

    private val prefs: Lazy<MMKV> = lazy {
        MMKV.mmkvWithID(PREFS_NAME)
    }

    var token by StringPreference(prefs, null)

    var skinIndex by IntPreference(prefs, SKIN_BLUE)

}