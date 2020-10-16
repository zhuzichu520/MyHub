package com.zhuzichu.android.repository.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.entity.arg.ArgWeb

class ViewModelRepository : ViewModelBase<ArgRepository>() {

    val title = MutableLiveData<String>()


}
