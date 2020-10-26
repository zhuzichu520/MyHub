package com.zhuzichu.android.shared.item

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

class ItemViewModelRepository(
    viewModel: ViewModelBase<*>,
    bean: BeanRepository
) : BaseItemViewModel(viewModel) {

    private val useCaseUpdateTrace by lazy {
        UseCaseUpdateTrace()
    }

    val id = bean.id

    val name = MutableLiveData(bean.name)

    val description = MutableLiveData(bean.description)

    val starsCount = MutableLiveData(bean.stargazersCount.toString())

    val forksCount = MutableLiveData(bean.forksCount.toString())

    val language = MutableLiveData(bean.language)

    val avatarUrl = MutableLiveData(bean.owner?.avatarUrl)

    val languageIcon = MutableLiveData(
        bean.language.toLanguageCircleDrawable()
    )

    val onClickItem = createCommand {
        useCaseUpdateTrace.execute(DataTrace.beanToData(bean))
            .life(viewModel)
            .subscribe {
                navigate(
                    RoutePath.Repository.ACTIVITY_REPOSITORY_MAIN,
                    ArgRepository.beanToArg(bean)
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