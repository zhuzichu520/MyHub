package com.zhuzichu.android.me.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.me.R
import com.zhuzichu.android.me.BR
import com.zhuzichu.android.me.databinding.FragmentTraceBinding
import com.zhuzichu.android.me.viewmodel.ViewModelTrace
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath
import kotlinx.android.synthetic.main.fragment_me.*

@Route(path = RoutePath.Me.FRAGMENT_ME_TRACE)
class FragmentTrace : FragmentBase<FragmentTraceBinding, ViewModelTrace, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_trace

    override fun initView() {
        super.initView()
        initTopBar()
    }

    private fun initTopBar() {
        topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
    }

}
