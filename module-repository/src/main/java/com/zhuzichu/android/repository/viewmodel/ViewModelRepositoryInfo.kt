package com.zhuzichu.android.repository.viewmodel

import androidx.lifecycle.MutableLiveData
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseGetReadme
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.entity.param.ParamGetReadme

class ViewModelRepositoryInfo : ViewModelBase<ArgRepository>() {

    val data = MutableLiveData<String>()

    private val useCaseGetReadme by lazy {
        UseCaseGetReadme()
    }

    fun loadMarkdown() {
        useCaseGetReadme.execute(
            ParamGetReadme(
                arg.login.toString(),
                arg.name.toString()
            )
        ).life(this).subscribe {
            data.value = it
        }
    }
}
