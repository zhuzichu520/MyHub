package com.zhuzichu.android.me.viewmodel

import androidx.lifecycle.Transformations
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.me.R
import com.zhuzichu.android.me.BR
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.db.daoTrace
import com.zhuzichu.android.shared.ext.itemBindingOf

class ViewModelTrace : ViewModelBase<ArgDefault>() {


    val items = Transformations.map(daoTrace().selectList()) {
        it.map { item ->
            ItemViewModelTrace(this, item)
        }
    }

    val itemBinding = itemBindingOf<ItemViewModelTrace>(BR.item, R.layout.item_trace)

}
