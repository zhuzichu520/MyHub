package com.zhuzichu.android.me.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.base.BaseItemViewModel
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.R
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseUpdateTrace
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.entity.data.DataTrace
import com.zhuzichu.android.shared.route.RoutePath

class ItemViewModelTraceUser(
    viewModel: ViewModelBase<*>,
    data: DataTrace
) : BaseItemViewModel(viewModel) {

    private val useCaseUpdateTrace by lazy {
        UseCaseUpdateTrace()
    }

    val name = MutableLiveData(data.login)

    val avatarUrl = MutableLiveData(data.avatarUrl)

    val onClickItem = createCommand {
        useCaseUpdateTrace.execute(data)
            .life(viewModel)
            .subscribe {
                toast(data.login.toString())
            }
    }

}