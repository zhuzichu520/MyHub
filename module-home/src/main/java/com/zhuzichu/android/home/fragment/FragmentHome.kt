package com.zhuzichu.android.home.fragment

import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.libs.internal.MainHandler
import com.hiwitech.android.mvvm.base.ArgDefault
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout
import com.zhuzichu.android.home.R
import com.zhuzichu.android.home.BR
import com.zhuzichu.android.home.databinding.FragmentHomeBinding
import com.zhuzichu.android.home.viewmodel.ViewModelHome
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath
import kotlinx.android.synthetic.main.fragment_home.*

@Route(path = RoutePath.Home.FRAGMENT_HOME_MAIN)
class FragmentHome : FragmentBase<FragmentHomeBinding, ViewModelHome, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_home

    override fun initData() {
        super.initData()
        viewModel.loadData()
    }

    override fun initListener() {
        super.initListener()
        content.setActionListener { action ->
            when (action.pullEdge) {
                QMUIPullLayout.PULL_EDGE_TOP -> {
                    viewModel.page = 1
                    viewModel.loadData {
                        content.finishActionRun(action)
                    }
                }
                QMUIPullLayout.PULL_EDGE_BOTTOM -> {
                    viewModel.loadData {
                        content.finishActionRun(action)
                    }
                }
            }
        }
    }

}
