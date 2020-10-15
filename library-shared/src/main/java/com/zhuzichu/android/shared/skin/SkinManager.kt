package com.zhuzichu.android.shared.skin

import android.content.Context
import android.content.res.Configuration
import com.qmuiteam.qmui.skin.QMUISkinManager
import com.zhuzichu.android.shared.R
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.shared.storage.AppStorage

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/29 10:50 AM
 * since: v 1.0.0
 */
class SkinManager {
    companion object {

        const val SKIN_BLUE = 1
        const val SKIN_DARK = 2

        fun install(context: Context) {
            val skinManager = QMUISkinManager.defaultInstance(context)
            skinManager.addSkin(
                SKIN_BLUE,
                R.style.app_skin_blue
            )
            skinManager.addSkin(
                SKIN_DARK,
                R.style.app_skin_dark
            )

            val isDarkMode =
                (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
            val storeSkinIndex: Int = AppStorage.skinIndex
            if (isDarkMode && storeSkinIndex != SKIN_DARK) {
                skinManager.changeSkin(SKIN_DARK)
            } else if (!isDarkMode && storeSkinIndex == SKIN_DARK) {
                skinManager.changeSkin(SKIN_BLUE)
            } else {
                skinManager.changeSkin(storeSkinIndex)
            }
        }


        fun changeSkin(index: Int) {
            QMUISkinManager.defaultInstance(context).changeSkin(index)
            AppStorage.skinIndex = index
        }

        fun getCurrentSkin(): Int {
            return QMUISkinManager.defaultInstance(context).currentSkin
        }

    }
}