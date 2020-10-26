package com.zhuzichu.android.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.search.R
import com.zhuzichu.android.search.BR
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.home.UseCaseGetRepos
import com.zhuzichu.android.shared.entity.arg.ArgSearch
import com.zhuzichu.android.shared.entity.enumeration.EnumEmptyStatus
import com.zhuzichu.android.shared.entity.param.ParamGetRepos
import com.zhuzichu.android.shared.ext.itemBindingOf
import com.zhuzichu.android.shared.item.ItemViewModelRepository

class ViewModelSearchResult : ViewModelBase<ArgSearch>() {

    val title = MutableLiveData<String>()

    private val useCaseGetRepos by lazy {
        UseCaseGetRepos()
    }


    private val pageSize = 20

    var page = 1

    val emptyStatus = MutableLiveData(EnumEmptyStatus.LOADING)

    val items = MutableLiveData<List<ItemViewModelRepository>>()

    val itemBinding = itemBindingOf<ItemViewModelRepository>(BR.item, R.layout.item_repo)

    val emptyErrorCommand = createCommand {
        emptyStatus.value = EnumEmptyStatus.LOADING
        loadData()
    }

    fun loadData(finally: (() -> Unit)? = null) {
        useCaseGetRepos.execute(ParamGetRepos(arg.keyword, page, pageSize))
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
