package com.zhuzichu.android.login.fragment

import android.Manifest
import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.libs.tool.jumpToSystemBrowser
import com.hiwitech.android.libs.tool.setOnClickListener
import com.hiwitech.android.mvvm.base.ArgDefault
import com.tbruyelle.rxpermissions3.RxPermissions
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath
import com.zhuzichu.android.login.R
import com.zhuzichu.android.login.BR
import com.zhuzichu.android.login.databinding.FragmentLoginBinding
import com.zhuzichu.android.login.viewmodel.ViewModelLogin
import com.zhuzichu.android.shared.entity.arg.ArgMain
import com.zhuzichu.android.shared.entity.enumeration.EnumMainType
import com.zhuzichu.android.shared.storage.AppStorage

@Route(path = RoutePath.Login.FRAGMENT_LOGIN_MAIN)
class FragmentLogin : FragmentBase<FragmentLoginBinding, ViewModelLogin, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_login

    override fun initView() {
        super.initView()
        AppStorage.token?.let {
            navigate(RoutePath.Main.ACTIVITY_MAIN_MAIN, ArgMain(EnumMainType.LOGIN), true)
        }
        requestPermission()
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.onLoginBrowserEvent.observe(viewLifecycleOwner) {
            jumpToSystemBrowser(requireContext(), it)
        }
    }

    private fun requestPermission() {
        RxPermissions(this).request(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe {

        }
    }

    override fun onNewIntent(intent: Intent?) {
        intent?.data?.let {
            val code = it.getQueryParameter("code")
            val state = it.getQueryParameter("state")
            viewModel.oauthToken(code, state)
        }
    }

}

