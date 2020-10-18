package com.zhuzichu.android.shared.base

import android.content.res.Configuration
import android.os.Bundle
import com.hiwitech.android.mvvm.base.BaseActivity
import com.qmuiteam.qmui.skin.QMUISkinManager
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.zhuzichu.android.shared.skin.SkinManager

abstract class ActivityBase : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val skinManager = QMUISkinManager .defaultInstance(this)
        setSkinManager(skinManager)
        SkinManager.onSkinChangeListener.observe(this) {
            if (it == SkinManager.SKIN_DARK) {
                QMUIStatusBarHelper.setStatusBarDarkMode(this)
            } else {
                QMUIStatusBarHelper.setStatusBarLightMode(this)
            }
        }
    }

}
