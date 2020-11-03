package com.zhuzichu.android.viewer.viewmodel

import androidx.lifecycle.MutableLiveData
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseGetRepoFile
import com.zhuzichu.android.shared.entity.arg.ArgViewer
import com.zhuzichu.android.shared.entity.enumeration.EnumFileAccept
import com.zhuzichu.android.shared.entity.param.ParamFile
import com.zhuzichu.android.shared.ext.autoLoading

class ViewModelViewer : ViewModelBase<ArgViewer>() {

    val data = MutableLiveData<String>()

    private val useCaseGetRepoFile by lazy {
        UseCaseGetRepoFile()
    }

    val title = MutableLiveData<String>()


    fun loadData() {
        useCaseGetRepoFile.execute(ParamFile(arg.url, EnumFileAccept.RAW))
            .map {
                formatCode(it)
            }
            .life(this)
            .subscribe {
                data.value = it
            }
    }

    private fun formatCode(codeSource: String): String {
        return if (codeSource.isBlank()) codeSource else codeSource.replace(
            "<".toRegex(), "&lt;"
        ).replace(">".toRegex(), "&gt;")
    }

}
