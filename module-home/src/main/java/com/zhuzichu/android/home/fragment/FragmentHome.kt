package com.zhuzichu.android.home.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.home.R
import com.zhuzichu.android.home.BR
import com.zhuzichu.android.home.databinding.FragmentHomeBinding
import com.zhuzichu.android.home.viewmodel.ViewModelHome
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath

@Route(path = RoutePath.Home.FRAGMENT_HOME_MAIN)
class FragmentHome : FragmentBase<FragmentHomeBinding, ViewModelHome, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_home

}
