package com.zhuzichu.android.repository.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.repository.R
import com.zhuzichu.android.repository.BR
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseGetRepoFileList
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.entity.enumeration.EnumEmptyStatus
import com.zhuzichu.android.shared.entity.param.ParamFileList
import com.zhuzichu.android.shared.ext.itemBindingOf

class ViewModelRepositoryFile : ViewModelBase<ArgRepository>() {

    private val useCaseGetRepositoryFile by lazy {
        UseCaseGetRepoFileList()
    }

    val items = MutableLiveData<List<ItemViewModelRepositoryFile>>()

    val itemBinding =
        itemBindingOf<ItemViewModelRepositoryFile>(BR.item, R.layout.item_repository_file)

    val emptyStatus = MutableLiveData(EnumEmptyStatus.LOADING)

    val emptyErrorCommand = createCommand {
        emptyStatus.value = EnumEmptyStatus.LOADING
        loadData()
    }

    val title = MutableLiveData<String>()

    fun loadData() {
        useCaseGetRepositoryFile.execute(
            ParamFileList(
                arg.login,
                arg.name,
                "/",
                "master"
            )
        ).life(this).subscribe(
            {
                emptyStatus.value = EnumEmptyStatus.SUCCESS
                items.value = it.map { item ->
                    ItemViewModelRepositoryFile(this, item)
                }
            },
            {
                emptyStatus.value = EnumEmptyStatus.ERROR
            }
        )
    }

}
