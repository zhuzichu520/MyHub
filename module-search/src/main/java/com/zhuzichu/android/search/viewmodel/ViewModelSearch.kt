package com.zhuzichu.android.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.entity.arg.ArgWeb

class ViewModelSearch : ViewModelBase<ArgDefault>() {

    val title = MutableLiveData<String>("搜索")


}
