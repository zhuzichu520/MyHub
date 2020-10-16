package com.zhuzichu.android.login.fragment

import android.content.res.Configuration
import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath
import com.zhuzichu.android.login.R
import com.zhuzichu.android.login.BR
import com.zhuzichu.android.login.databinding.FragmentLoginBinding
import com.zhuzichu.android.login.viewmodel.ViewModelLogin
import com.zhuzichu.android.shared.storage.AppStorage

@Route(path = RoutePath.Login.FRAGMENT_LOGIN_MAIN)
class FragmentLogin : FragmentBase<FragmentLoginBinding, ViewModelLogin, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_login

    override fun initView() {
        super.initView()
        AppStorage.token?.let {
            viewModel.startMain()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

    }

}
