package com.zhuzichu.android.me.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.me.R
import com.zhuzichu.android.me.BR
import com.zhuzichu.android.me.databinding.FragmentMeBinding
import com.zhuzichu.android.me.viewmodel.ViewModelMe
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath

@Route(path = RoutePath.Me.FRAGMENT_ME_MAIN)
class FragmentMe : FragmentBase<FragmentMeBinding, ViewModelMe, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_me

}
