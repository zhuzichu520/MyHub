package com.zhuzichu.android.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.libs.internal.MainHandler
import com.hiwitech.android.libs.tool.toStringEmpty
import com.hiwitech.android.mvvm.base.ArgDefault
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.login.UseCaseAuthorizations
import com.zhuzichu.android.shared.entity.arg.ArgMain
import com.zhuzichu.android.shared.entity.enumeration.EnumMainType
import com.zhuzichu.android.shared.entity.param.ParamAuthor
import com.zhuzichu.android.shared.ext.autoLoading
import com.zhuzichu.android.shared.route.RoutePath
import com.zhuzichu.android.shared.storage.AppStorage
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
        AppStorage.token = basicToken
        useCaseAuthorizations.execute(
            ParamAuthor()
        ).autoLoading(this).life(this).subscribe(
            {
                AppStorage.token = "token ${it.token}"
                MainHandler.postDelayed {
                    startMain()
                }
            },
            {
                toast(it.message.toString())
                it.printStackTrace()
            }
        )
    }


    fun startMain() {
        navigate(RoutePath.Main.ACTIVITY_MAIN_MAIN, ArgMain(EnumMainType.LOGIN), true)
    }

}
