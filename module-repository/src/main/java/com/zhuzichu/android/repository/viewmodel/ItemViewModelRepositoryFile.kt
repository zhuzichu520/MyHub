package com.zhuzichu.android.repository.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.libs.tool.byteCountToDisplaySizeTwo
import com.hiwitech.android.libs.tool.toLong
import com.hiwitech.android.mvvm.base.BaseItemViewModel
import com.hiwitech.android.mvvm.base.BaseViewModel
import com.hiwitech.android.mvvm.ext.createCommand
import com.zhuzichu.android.repository.R
import com.zhuzichu.android.shared.entity.arg.ArgViewer
import com.zhuzichu.android.shared.entity.bean.BeanFile
import com.zhuzichu.android.shared.entity.enumeration.EnumFileType
import com.zhuzichu.android.shared.route.RoutePath

/**
 * desc
 * author: 朱子楚
 * time: 2020/11/2 5:00 PM
 * since: v 1.0.0
 */
class ItemViewModelRepositoryFile(
    viewModel: BaseViewModel<*>,
    bean: BeanFile
) : BaseItemViewModel(viewModel) {

    val icon = MutableLiveData<Int>().apply {
        value = when (bean.type) {
            EnumFileType.DIR -> {
                R.drawable.ic_repo_dir
            }
            EnumFileType.FILE -> {
                R.drawable.ic_repo_file
            }
            else -> {
                null
            }
        }
    }

    val visibilitySize = MutableLiveData<Int>().apply {
        value = if (bean.type == EnumFileType.FILE) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    val size = MutableLiveData<String>().apply {
        value = byteCountToDisplaySizeTwo(toLong(bean.size))
    }

    val name = MutableLiveData<String>().apply {
        value = bean.name
    }

    val onClickCommand = createCommand {
        navigate(RoutePath.Viewer.ACTIVITY_VIEWER_MAIN, ArgViewer(bean.url.toString(), bean.name))
    }


}