package com.zhuzichu.android.search.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.afollestad.recyclical.datasource.DataSource
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.zhuzichu.android.search.R
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.entity.arg.ArgSearch
import com.zhuzichu.android.shared.entity.param.ParamSearchRepositories
import com.zhuzichu.android.shared.entity.param.ParamSearchUsers

class ViewModelSearchResult : ViewModelBase<ArgSearch>() {

    val title = MutableLiveData<String>()

}
