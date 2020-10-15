package com.zhuzichu.android.me.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.me.R
import com.zhuzichu.android.me.BR
import com.zhuzichu.android.me.databinding.FragmentMeBinding
import com.zhuzichu.android.me.viewmodel.ViewModelMe
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath
import kotlinx.android.synthetic.main.fragment_me.*

@Route(path = RoutePath.Me.FRAGMENT_ME_MAIN)
class FragmentMe : FragmentBase<FragmentMeBinding, ViewModelMe, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_me

    override fun initView() {
        super.initView()
        initTopBar()
    }

    private fun initTopBar() {
        topbar.addRightImageButton(R.drawable.ic_topbar_setting, R.id.topbar_right_setting_button)
            .setOnClickListener {
                navigate(RoutePath.Setting.ACTIVITY_SETTING_MAIN)
            }
    }
}
