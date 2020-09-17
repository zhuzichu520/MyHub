package com.zhuzichu.android.main.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.main.R
import com.zhuzichu.android.main.BR
import com.zhuzichu.android.main.databinding.FragmentMainBinding
import com.zhuzichu.android.main.viewmodel.ViewModelMain
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath

@Route(path = RoutePath.Main.FRAGMENT_MAIN_MAIN)
class FragmentMain : FragmentBase<FragmentMainBinding, ViewModelMain, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_main

}
