package com.zhuzichu.android.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.search.R
import com.zhuzichu.android.search.BR
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseSearchUsers
import com.zhuzichu.android.shared.entity.arg.ArgSearch
import com.zhuzichu.android.shared.entity.enumeration.EnumEmptyStatus
import com.zhuzichu.android.shared.entity.param.ParamSearchUsers
import com.zhuzichu.android.shared.ext.itemBindingOf
import com.zhuzichu.android.shared.item.ItemViewModelUser

class ViewModelSearchUsers : ViewModelBase<ArgSearch>() {

    private val useCaseSearchUsers by lazy {
        UseCaseSearchUsers()
    }

    lateinit var share: ShareViewModel

    private val pageSize = 20

    var page = 1

    val emptyStatus = MutableLiveData(EnumEmptyStatus.LOADING)

    val items = MutableLiveData<List<ItemViewModelUser>>()

    val itemBinding = itemBindingOf<ItemViewModelUser>(BR.item, R.layout.item_user)

    val emptyErrorCommand = createCommand {
        emptyStatus.value = EnumEmptyStatus.LOADING
        loadData()
    }

    fun loadData(finally: (() -> Unit)? = null) {
        val searchMode = share.getSearchMode() ?: return
        useCaseSearchUsers.execute(ParamSearchUsers(arg.keyword, page, pageSize, searchMode.sort, searchMode.order))
            .doFinally {
                finally?.invoke()
            }
            .life(this)
            .subscribe(
                {
                    emptyStatus.value = EnumEmptyStatus.SUCCESS
                    val data = it.items?.map { item ->
                        ItemViewModelUser(this, item)
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
