package com.zhuzichu.android.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.entity.arg.ArgSearch

class ViewModelSearchResult : ViewModelBase<ArgSearch>() {

    val title = MutableLiveData<String>()

}
