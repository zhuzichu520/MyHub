package com.zhuzichu.android.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.search.R
import com.zhuzichu.android.search.BR
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseSearchRepositories
import com.zhuzichu.android.shared.entity.arg.ArgSearch
import com.zhuzichu.android.shared.entity.enumeration.EnumEmptyStatus
import com.zhuzichu.android.shared.entity.param.ParamSearchRepositories
import com.zhuzichu.android.shared.ext.itemBindingOf
import com.zhuzichu.android.shared.item.ItemViewModelRepository

class ViewModelSearchRepositories : ViewModelBase<ArgSearch>() {

    private val useCaseGetRepos by lazy {
        UseCaseSearchRepositories()
    }

    lateinit var share: ShareViewModel

    private val pageSize = 20

    var page = 1

    val emptyStatus = MutableLiveData(EnumEmptyStatus.LOADING)

    val items = MutableLiveData<List<ItemViewModelRepository>>()

    val itemBinding = itemBindingOf<ItemViewModelRepository>(BR.item, R.layout.item_repository)

    val emptyErrorCommand = createCommand {
        emptyStatus.value = EnumEmptyStatus.LOADING
        loadData()
    }

    fun loadData(finally: (() -> Unit)? = null) {
        val searchMode = share.getSearchMode() ?: return
        useCaseGetRepos.execute(ParamSearchRepositories(arg.keyword, page, pageSize, searchMode.sort, searchMode.order))
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
