package com.zhuzichu.android.home.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.qmuiteam.qmui.recyclerView.QMUIRVDraggableScrollBar
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout
import com.zhuzichu.android.home.BR
import com.zhuzichu.android.home.R
import com.zhuzichu.android.home.databinding.FragmentHomeBinding
import com.zhuzichu.android.home.viewmodel.ViewModelHome
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath
import kotlinx.android.synthetic.main.fragment_home.*

@Route(path = RoutePath.Home.FRAGMENT_HOME_MAIN)
class FragmentHome : FragmentBase<FragmentHomeBinding, ViewModelHome, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        super.initView()
        initTopBar()
        val scrollBar = QMUIRVDraggableScrollBar(0, 0, 0)
        scrollBar.isEnableScrollBarFadeInOut = true
        scrollBar.attachToRecyclerView(recycler)
    }

    private fun initTopBar() {
        topbar.addRightImageButton(R.drawable.ic_topbar_search, R.id.topbar_right_search_button)
            .setOnClickListener {
                navigate(RoutePath.Search.ACTIVITY_SEARCH_MAIN)
            }
    }

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
