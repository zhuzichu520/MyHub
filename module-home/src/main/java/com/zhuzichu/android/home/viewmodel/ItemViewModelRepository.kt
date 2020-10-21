package com.zhuzichu.android.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.base.BaseItemViewModel
import com.hiwitech.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.home.R
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.entity.arg.ArgWeb
import com.zhuzichu.android.shared.entity.bean.BeanRepository
import com.zhuzichu.android.shared.route.RoutePath

class ItemViewModelRepository(
    viewModel: ViewModelHome,
    bean: BeanRepository
) : BaseItemViewModel(viewModel) {

    val id = bean.id

    val name = MutableLiveData(bean.name)

    val description = MutableLiveData(bean.description)

    val starsCount = MutableLiveData(bean.stargazersCount.toString())

    val forksCount = MutableLiveData(bean.forksCount.toString())

    val language = MutableLiveData(bean.language)

    val languageIcon = MutableLiveData(
        bean.language.toLanguageCircleDrawable()
    )

    val onClickItem = BindingCommand<Any>({
        navigate(
            RoutePath.Repository.ACTIVITY_REPOSITORY_MAIN,
            ArgRepository(bean)
        )
    })

    private fun String?.toLanguageCircleDrawable(): Int {
        return when (this) {
            "Kotlin" -> R.drawable.ic_circle_kotlin
            "Java" -> R.drawable.ic_circle_java
            else -> R.drawable.ic_circle_other
        }
    }

}