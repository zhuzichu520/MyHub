package com.zhuzichu.android.web.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.entity.arg.ArgWeb

class ViewModelWeb : ViewModelBase<ArgWeb>() {

    val title = MutableLiveData<String>()


}
