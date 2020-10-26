package com.zhuzichu.android.me.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.base.BaseItemViewModel
import com.hiwitech.android.mvvm.databinding.BindingCommand
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.R
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.trace.UseCaseUpdateTrace
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.entity.bean.BeanRepository
import com.zhuzichu.android.shared.entity.data.DataTrace
import com.zhuzichu.android.shared.route.RoutePath

class ItemViewModelTrace(
    viewModel: ViewModelBase<*>,
    data: DataTrace
) : BaseItemViewModel(viewModel) {

    private val useCaseUpdateTrace by lazy {
        UseCaseUpdateTrace()
    }

    val id = data.repositoryId

    val name = MutableLiveData(data.name)

    val description = MutableLiveData(data.description)

    val starsCount = MutableLiveData(data.stargazersCount.toString())

    val forksCount = MutableLiveData(data.forksCount.toString())

    val language = MutableLiveData(data.language)

    val avatarUrl = MutableLiveData(data.avatarUrl)

    val languageIcon = MutableLiveData(
        data.language.toLanguageCircleDrawable()
    )

    val onClickItem = createCommand {
        useCaseUpdateTrace.execute(data)
            .life(viewModel)
            .subscribe {
                navigate(
                    RoutePath.Repository.ACTIVITY_REPOSITORY_MAIN,
                    ArgRepository.dataToArg(data)
                )
            }
    }

    private fun String?.toLanguageCircleDrawable(): Int {
        return when (this) {
            "Kotlin" -> R.drawable.ic_circle_kotlin
            "Java" -> R.drawable.ic_circle_java
            else -> R.drawable.ic_circle_other
        }
    }

}