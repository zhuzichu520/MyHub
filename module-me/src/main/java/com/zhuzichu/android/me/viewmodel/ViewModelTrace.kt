package com.zhuzichu.android.me.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.base.ArgDefault
import com.rxjava.rxlife.life
import com.zhuzichu.android.me.BR
import com.zhuzichu.android.me.R
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseGetTrace
import com.zhuzichu.android.shared.entity.data.DataTrace
import com.zhuzichu.android.shared.entity.param.ParamPager
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class ViewModelTrace : ViewModelBase<ArgDefault>() {

    val pageSize = 20

    var page = 1

    val items = MutableLiveData<List<Any>>()

    private val useCaseGetTrace by lazy {
        UseCaseGetTrace()
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map(ItemViewModelTraceRepository::class.java, BR.item, R.layout.item_trace_repository)
        map(ItemViewModelTraceUser::class.java, BR.item, R.layout.item_trace_user)
    }

    fun loadData(finally: (() -> Unit)? = null) {
        useCaseGetTrace.execute(ParamPager(page, pageSize))
            .doFinally {
                finally?.invoke()
            }
            .life(this)
            .subscribe(
                {
                    val data = it.map { item ->
                        when (item.type) {
                            DataTrace.TYPE_USER -> {
                                ItemViewModelTraceUser(this, item)
                            }
                            else -> {
                                ItemViewModelTraceRepository(this, item)
                            }
                        }
                    }
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
                    it.printStackTrace()
                }
            )
    }

}
