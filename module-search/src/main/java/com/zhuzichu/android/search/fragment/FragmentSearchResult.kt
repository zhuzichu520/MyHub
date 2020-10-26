package com.zhuzichu.android.search.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.qmuiteam.qmui.recyclerView.QMUIRVDraggableScrollBar
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout
import com.zhuzichu.android.search.R
import com.zhuzichu.android.search.BR
import com.zhuzichu.android.search.databinding.FragmentSearchResultBinding
import com.zhuzichu.android.search.viewmodel.ViewModelSearchResult
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.entity.arg.ArgSearch
import com.zhuzichu.android.shared.route.RoutePath
import kotlinx.android.synthetic.main.fragment_search_result.*

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/26 2:27 PM
 * since: v 1.0.0
 */

@Route(path = RoutePath.Search.FRAGMENT_SEARCH_RESULT)
class FragmentSearchResult : FragmentBase<FragmentSearchResultBinding, ViewModelSearchResult, ArgSearch>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_search_result

    override fun initView() {
        super.initView()
        initTopBar()
        val scrollBar = QMUIRVDraggableScrollBar(0, 0, 0)
        scrollBar.isEnableScrollBarFadeInOut = true
        scrollBar.attachToRecyclerView(recycler)
    }

    override fun initLazyData() {
        viewModel.title.value = arg.keyword
        viewModel.loadData()
    }

    private fun initTopBar() {
        topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
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