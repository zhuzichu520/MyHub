package com.zhuzichu.android.repository.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.event.SingleLiveEvent
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.repository.R
import com.zhuzichu.android.repository.BR
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseGetRepoFileList
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.entity.bean.BeanFile
import com.zhuzichu.android.shared.entity.enumeration.EnumEmptyStatus
import com.zhuzichu.android.shared.entity.param.ParamFileList
import com.zhuzichu.android.shared.ext.itemBindingOf

class ViewModelRepositoryFile : ViewModelBase<ArgRepository>() {

    private val useCaseGetRepositoryFile by lazy {
        UseCaseGetRepoFileList()
    }

    var bean = BeanFile(name = "/", path = "/")

    val items = MutableLiveData<List<ItemViewModelRepositoryFile>>()

    val itemBinding =
        itemBindingOf<ItemViewModelRepositoryFile>(BR.item, R.layout.item_repository_file)

    val itemsTab = ObservableArrayList<ItemViewModelRepositoryTab>()

    val itemBindingTab =
        itemBindingOf<ItemViewModelRepositoryTab>(BR.item, R.layout.item_repository_tab)

    val emptyStatus = MutableLiveData(EnumEmptyStatus.LOADING)

    val emptyErrorCommand = createCommand {
        emptyStatus.value = EnumEmptyStatus.LOADING
        loadData()
    }

    val onTabAddEvent = SingleLiveEvent<Unit>()

    val onClickTabListener: (BeanFile) -> Unit = tab@{ tab ->
        itemsTab.reversed().forEach { item ->
            if (item.bean.path == tab.path) {
                this.bean = tab
                loadData()
                return@tab
            }
            itemsTab.remove(item)
        }
    }

    /**
     * 点击文件夹
     */
    private val onClickDirListener: (BeanFile) -> Unit = { bean ->
        itemsTab.add(ItemViewModelRepositoryTab(this, bean, onClickTabListener))
        this.bean = bean
        loadData()
        onTabAddEvent.call()
    }

    fun loadData() {
        emptyStatus.value = EnumEmptyStatus.LOADING
        useCaseGetRepositoryFile.execute(
            ParamFileList(
                arg.login,
                arg.name,
                bean.path,
                "master"
            )
        ).life(this).subscribe(
            {
                emptyStatus.value = EnumEmptyStatus.SUCCESS
                items.value = it.map { item ->
                    ItemViewModelRepositoryFile(this, item, onClickDirListener)
                }
            },
            {
                emptyStatus.value = EnumEmptyStatus.ERROR
            }
        )
    }

}
