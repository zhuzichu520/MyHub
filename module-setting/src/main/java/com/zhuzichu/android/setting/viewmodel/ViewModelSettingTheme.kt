package com.zhuzichu.android.setting.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.shared.base.ViewModelBase

class ViewModelSettingTheme : ViewModelBase<ArgDefault>() {

    val name = MutableLiveData<String>()

    val version = MutableLiveData<String>()

    val current = MutableLiveData<Int>()

}
