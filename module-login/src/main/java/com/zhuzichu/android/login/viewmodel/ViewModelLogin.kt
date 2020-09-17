package com.zhuzichu.android.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.libs.internal.MainHandler
import com.hiwitech.android.libs.tool.toStringEmpty
import com.hiwitech.android.mvvm.base.ArgDefault
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.login.UseCaseAuthorizations
import com.zhuzichu.android.shared.entity.param.ParamAuthor
import com.zhuzichu.android.shared.ext.autoLoading
import com.zhuzichu.android.shared.route.RoutePath
import okhttp3.Credentials

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
                MainHandler.postDelayed {
                    finish()
                    navigate(RoutePath.Main.ACTIVITY_MAIN_MAIN)
                }
            },
            {
                toast(it.message.toString())
                it.printStackTrace()
            }
        )
    }

}
