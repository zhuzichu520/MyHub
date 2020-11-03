package com.zhuzichu.android.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.DiffUtil
import com.hiwitech.android.mvvm.base.ArgDefault
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.search.R
import com.zhuzichu.android.search.BR
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.db.daoHistory
import com.zhuzichu.android.shared.domain.UseCaseDeleteSearchHistory
import com.zhuzichu.android.shared.domain.UseCaseUpdateSearchHistory
import com.zhuzichu.android.shared.entity.arg.ArgSearch
import com.zhuzichu.android.shared.entity.data.DataSearchHistory
import com.zhuzichu.android.shared.ext.autoLoading
import com.zhuzichu.android.shared.ext.diffEquals
import com.zhuzichu.android.shared.ext.itemBindingOf
import com.zhuzichu.android.shared.route.RoutePath

class ViewModelSearch : ViewModelBase<ArgDefault>() {

    private val useCaseUpdateSearchHistory by lazy {
        UseCaseUpdateSearchHistory()
    }

    private val useCaseDeleteSearchHistory by lazy {
        UseCaseDeleteSearchHistory()
    }

    val android = "Android"

    val hint = MutableLiveData(android)

    val items: LiveData<List<ItemViewModelSearcHistory>> =
        Transformations.map(daoHistory().selectList()) {
            it.map { item ->
                ItemViewModelSearcHistory(this, item)
            }
        }

    val diff = object : DiffUtil.ItemCallback<ItemViewModelSearcHistory>() {
        override fun areItemsTheSame(
            oldItem: ItemViewModelSearcHistory,
            newItem: ItemViewModelSearcHistory
        ): Boolean {
            return newItem.data.id == oldItem.data.id
        }

        override fun areContentsTheSame(
            oldItem: ItemViewModelSearcHistory,
            newItem: ItemViewModelSearcHistory
        ): Boolean =
            oldItem.diffEquals(newItem)
    }

    val itemBinding = itemBindingOf<ItemViewModelSearcHistory>(BR.item, R.layout.item_search_hisory)

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
        useCaseUpdateSearchHistory.execute(DataSearchHistory(name = keyword))
            .life(this)
            .subscribe {
                navigate(RoutePath.Search.FRAGMENT_SEARCH_RESULT, ArgSearch(keyword))
            }
    }

    /**
     * 清空历史记录
     */
    val onDeleteCommand = createCommand {
        items.value?.let {
            useCaseDeleteSearchHistory.execute(it.map { item ->
                item.data
            }).autoLoading(this).life(this).subscribe {

            }
        }
    }
}
