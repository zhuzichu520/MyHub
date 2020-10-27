package com.zhuzichu.android.me.viewmodel

import androidx.lifecycle.Transformations
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.me.R
import com.zhuzichu.android.me.BR
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.db.daoTrace
import com.zhuzichu.android.shared.entity.data.DataTrace
import com.zhuzichu.android.shared.ext.itemBindingOf
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class ViewModelTrace : ViewModelBase<ArgDefault>() {

    val items = Transformations.map(daoTrace().selectList()) {
        val data = arrayListOf<Any>()
        it.forEach { item ->
            when (item.type) {
                DataTrace.TYPE_USER -> {
                    data.add(ItemViewModelTraceUser(this, item))
                }
                DataTrace.TYPE_REPOSITORY -> {
                    data.add(ItemViewModelTraceRepository(this, item))
                }
            }
        }
        data
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map(ItemViewModelTraceRepository::class.java, BR.item, R.layout.item_trace_repository)
        map(ItemViewModelTraceUser::class.java, BR.item, R.layout.item_trace_user)
    }

}
