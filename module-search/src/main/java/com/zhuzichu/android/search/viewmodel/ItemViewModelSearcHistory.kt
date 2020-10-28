package com.zhuzichu.android.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.libs.internal.MainHandler
import com.hiwitech.android.mvvm.base.BaseItemViewModel
import com.hiwitech.android.mvvm.base.BaseViewModel
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.domain.UseCaseUpdateSearchHistory
import com.zhuzichu.android.shared.entity.arg.ArgSearch
import com.zhuzichu.android.shared.entity.data.DataSearchHistory
import com.zhuzichu.android.shared.route.RoutePath

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/28 2:42 PM
 * since: v 1.0.0
 */
class ItemViewModelSearcHistory(
    viewModel: BaseViewModel<*>,
    val data: DataSearchHistory
) : BaseItemViewModel(viewModel) {

    private val useCaseUpdateSearchHistory by lazy {
        UseCaseUpdateSearchHistory()
    }

    val name = MutableLiveData<String>(data.name)

    val onClickCommand = createCommand {
        val keyword = data.name.toString()
        useCaseUpdateSearchHistory.execute(data)
            .life(viewModel)
            .subscribe {
                navigate(RoutePath.Search.FRAGMENT_SEARCH_RESULT, ArgSearch(keyword))
            }
    }

}