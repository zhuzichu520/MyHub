package com.zhuzichu.android.shared.item

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.base.BaseItemViewModel
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.R
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseUpdateTrace
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.entity.bean.BeanOwner
import com.zhuzichu.android.shared.entity.bean.BeanRepository
import com.zhuzichu.android.shared.entity.data.DataTrace
import com.zhuzichu.android.shared.route.RoutePath

class ItemViewModelUser(
    viewModel: ViewModelBase<*>,
    bean: BeanOwner
) : BaseItemViewModel(viewModel) {

    private val useCaseUpdateTrace by lazy {
        UseCaseUpdateTrace()
    }

    val name = MutableLiveData(bean.login)

    val avatarUrl = MutableLiveData(bean.avatarUrl)

    val onClickItem = createCommand {
        useCaseUpdateTrace.execute(DataTrace.beanToData(bean))
            .life(viewModel)
            .subscribe {
                toast(bean.login.toString())
            }
    }

}