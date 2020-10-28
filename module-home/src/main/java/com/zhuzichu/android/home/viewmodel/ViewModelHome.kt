package com.zhuzichu.android.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.hiwitech.android.mvvm.base.ArgDefault
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.home.R
import com.zhuzichu.android.home.BR
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseSearchRepositories
import com.zhuzichu.android.shared.entity.enumeration.EnumEmptyStatus
import com.zhuzichu.android.shared.entity.param.ParamSearchRepositories
import com.zhuzichu.android.shared.ext.itemBindingOf
import com.zhuzichu.android.shared.item.ItemViewModelRepository

class ViewModelHome : ViewModelBase<ArgDefault>() {

    private val pageSize = 20

    var page = 1

    private val useCaseGetRepos by lazy {
        UseCaseSearchRepositories()
    }

    val emptyStatus = MutableLiveData(EnumEmptyStatus.LOADING)

    val items = MutableLiveData<List<ItemViewModelRepository>>()

    val itemBinding = itemBindingOf<ItemViewModelRepository>(BR.item, R.layout.item_repository)

    val emptyErrorCommand = createCommand {
        emptyStatus.value = EnumEmptyStatus.LOADING
        loadData()
    }

    fun loadData(finally: (() -> Unit)? = null) {
        useCaseGetRepos.execute(ParamSearchRepositories("Android", page, pageSize))
            .doFinally {
                finally?.invoke()
            }
            .life(this)
            .subscribe(
                {
                    emptyStatus.value = EnumEmptyStatus.SUCCESS
                    val data = it.items?.map { item ->
                        ItemViewModelRepository(this, item)
                    } ?: return@subscribe
                    if (page == 1) {
                        items.value = data
                    } else {
                        items.value?.let { list ->
                            items.value = list + data
                        }
                    }
                    page += 1
                },
                {
                    emptyStatus.value = EnumEmptyStatus.ERROR
                }
            )
    }

}
