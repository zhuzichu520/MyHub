package com.zhuzichu.android.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.libs.internal.MainHandler
import com.hiwitech.android.libs.tool.object2Json
import com.hiwitech.android.libs.tool.toStringEmpty
import com.hiwitech.android.mvvm.base.ArgDefault
import com.hiwitech.android.mvvm.domain.UseCase
import com.hiwitech.android.mvvm.ext.createCommand
import com.hiwitech.android.widget.log.lumberjack.L
import com.hiwitech.android.widget.toast.toast
import com.rxjava.rxlife.life
import com.zhuzichu.android.login.entity.ParamterAuthorizations
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.login.UseCaseAuthorizations
import com.zhuzichu.android.shared.entity.param.ParamAuthor
import com.zhuzichu.android.shared.ext.autoLoading
import com.zhuzichu.android.shared.ext.bindToSchedulers
import okhttp3.Credentials
import rxhttp.RxHttp

class ViewModelLogin : ViewModelBase<ArgDefault>() {

    val username = MutableLiveData<String>("zhuzichu520")

    val password = MutableLiveData<String>("zhuzichu123")

    private val useCaseAuthorizations by lazy {
        UseCaseAuthorizations()
    }

    val onClickLoginCommand = createCommand {
        val basicToken = Credentials.basic(
            username.value.toStringEmpty(),
            password.value.toStringEmpty()
        )
        useCaseAuthorizations.execute(
            ParamAuthor(basicToken)
        ).autoLoading(this).life(this).subscribe(
            {
                hideLoading()
                toast(it.toString())
            },
            {
                toast(it.message.toString())
                it.printStackTrace()
            }
        )
    }

}
