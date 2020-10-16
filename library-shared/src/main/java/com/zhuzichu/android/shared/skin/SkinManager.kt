package com.zhuzichu.android.shared.skin

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate.*
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
            applyConfigurationChanged(context.resources.configuration)
        }


         fun changeSkin(index: Int) {
            QMUISkinManager.defaultInstance(context).changeSkin(index)
        }

        private fun getCurrentSkin(): Int {
            return QMUISkinManager.defaultInstance(context).currentSkin
        }

        fun applyConfigurationChanged(newConfig: Configuration) {
            when (AppStorage.uiMode) {
                MODE_NIGHT_FOLLOW_SYSTEM -> {
                    //系统默认
                    if (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
                        changeSkin(SKIN_DARK)
                    } else if (getCurrentSkin() == SKIN_DARK) {
                        changeSkin(SKIN_BLUE)
                    }
                }
                MODE_NIGHT_NO -> {
                    //白天模式
                    changeSkin(SKIN_BLUE)
                }
                MODE_NIGHT_YES -> {
                    //夜间模式
                    changeSkin(SKIN_DARK)
                }
            }
        }

    }
}