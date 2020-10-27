package com.zhuzichu.android.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.base.ArgDefault
import com.hiwitech.android.mvvm.ext.createCommand
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.entity.arg.ArgSearch
import com.zhuzichu.android.shared.entity.arg.ArgWeb
import com.zhuzichu.android.shared.route.RoutePath

class ViewModelSearch : ViewModelBase<ArgDefault>() {

    val android = "Android"

    val hint = MutableLiveData(android)

    /**
     * 搜索关键字
     */
    val keyword = MutableLiveData<String>()

    /**
     * 点击搜索事件
     */
    val onClickCommand = createCommand {
        var keyword = keyword.value
        keyword = if (!keyword.isNullOrBlank()) {
            keyword
        } else {
            android
        }
        navigate(RoutePath.Search.FRAGMENT_SEARCH_RESULT, ArgSearch(keyword))
    }

}
