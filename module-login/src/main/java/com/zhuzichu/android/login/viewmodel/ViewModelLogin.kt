package com.zhuzichu.android.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.libs.internal.MainHandler
import com.hiwitech.android.libs.tool.toStringEmpty
import com.hiwitech.android.mvvm.base.ArgDefault
import com.hiwitech.android.mvvm.event.SingleLiveEvent
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseAuthorizations
import com.zhuzichu.android.shared.domain.UseCaseLoginBrowser
import com.zhuzichu.android.shared.domain.UseCaseOauthToken
import com.zhuzichu.android.shared.entity.arg.ArgMain
import com.zhuzichu.android.shared.entity.enumeration.EnumMainType
import com.zhuzichu.android.shared.entity.param.ParamAuthor
import com.zhuzichu.android.shared.entity.param.ParamOauthToken
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

    private val useCaseLoginBrowser by lazy {
        UseCaseLoginBrowser()
    }

    private val useCaseOauthToken by lazy {
        UseCaseOauthToken()
    }

    val onLoginBrowserEvent = SingleLiveEvent<String>()

    val onClickLoginCommand = createCommand {
        val basicToken = Credentials.basic(
            username.value.toStringEmpty(),
            password.value.toStringEmpty()
        )
        authorizations(basicToken)
    }


    val onClickBrowserCommand = createCommand {
        useCaseLoginBrowser.execute(Unit)
            .life(this)
            .subscribe {
                onLoginBrowserEvent.value = it
            }
    }

    /**
     * 直接登录授权
     */
    private fun authorizations(basicToken: String) {
        useCaseAuthorizations.execute(
            ParamAuthor(basicToken = basicToken)
        ).autoLoading(this).life(this).subscribe(
            {
                handleSuccess(it.token)
            },
            {
                toast(it.message.toString())
                it.printStackTrace()
            }
        )
    }

    /**
     * 从浏览器登录授权
     */
    fun oauthToken(code: String?, state: String?) {
        useCaseOauthToken.execute(ParamOauthToken(code, state))
            .autoLoading(this)
            .life(this)
            .subscribe(
                {
                    handleSuccess(it.accessToken)
                },
                {
                    toast(it.message.toString())
                    it.printStackTrace()
                }
            )
    }


    private fun handleSuccess(token: String?) {
        AppStorage.token = token
        MainHandler.postDelayed {
            navigate(RoutePath.Main.ACTIVITY_MAIN_MAIN, ArgMain(EnumMainType.LOGIN), true)
        }
    }

}
