package com.zhuzichu.android.launcher.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.launcher.R
import com.zhuzichu.android.launcher.BR
import com.zhuzichu.android.launcher.databinding.FragmentLauncherBinding
import com.zhuzichu.android.launcher.viewmodel.ViewModelLauncher
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath


@Route(path = RoutePath.Launcher.FRAGMENT_LAUNCHER_MAIN)
class FragmentLauncher : FragmentBase<FragmentLauncherBinding, ViewModelLauncher, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_launcher

    override fun initView() {
        super.initView()
        navigate(RoutePath.Login.ACTIVITY_LOGIN_MAIN,isPop = true)
    }
}
