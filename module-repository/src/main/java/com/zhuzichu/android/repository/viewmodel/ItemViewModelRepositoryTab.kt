package com.zhuzichu.android.repository.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.base.BaseItemViewModel
import com.hiwitech.android.mvvm.base.BaseViewModel
import com.hiwitech.android.mvvm.ext.createCommand
import com.zhuzichu.android.shared.entity.bean.BeanFile

/**
 * desc
 * author: 朱子楚
 * time: 2020/11/2 5:00 PM
 * since: v 1.0.0
 */
class ItemViewModelRepositoryTab(
    viewModel: BaseViewModel<*>,
    val bean: BeanFile,
    onClickListener: ((BeanFile) -> Unit)? = null
) : BaseItemViewModel(viewModel) {

    val name = MutableLiveData<String>(bean.name)

    val onClickCommand = createCommand {
        onClickListener?.invoke(bean)
    }

}