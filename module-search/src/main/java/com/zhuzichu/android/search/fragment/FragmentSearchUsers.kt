package com.zhuzichu.android.search.fragment

import androidx.fragment.app.activityViewModels
import com.qmuiteam.qmui.recyclerView.QMUIRVDraggableScrollBar
import com.qmuiteam.qmui.widget.pullLayout.QMUIPullLayout
import com.zhuzichu.android.search.R
import com.zhuzichu.android.search.BR
import com.zhuzichu.android.search.databinding.FragmentSearchUsersBinding
import com.zhuzichu.android.search.viewmodel.ShareViewModel
import com.zhuzichu.android.search.viewmodel.ViewModelSearchUsers
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.entity.arg.ArgSearch

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/27 10:06 AM
 * since: v 1.0.0
 */
class FragmentSearchUsers :
    FragmentBase<FragmentSearchUsersBinding, ViewModelSearchUsers, ArgSearch>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_search_users

    private val share by activityViewModels<ShareViewModel>()

    override fun initView() {
        super.initView()
        val scrollBar = QMUIRVDraggableScrollBar(0, 0, 0)
        scrollBar.isEnableScrollBarFadeInOut = true
        scrollBar.attachToRecyclerView(binding.recycler)
    }

    override fun initArgs(arg: ArgSearch) {
        super.initArgs(arg)
        viewModel.share = share
    }

    override fun initLazyData() {
        viewModel.loadData()
    }

    override fun initViewObservable() {
        super.initViewObservable()
        share.onUsersSearchChangeEvent.observe(viewLifecycleOwner) {
            showLoading()
            viewModel.page = 1
            viewModel.loadData {
                hideLoading()
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.content.setActionListener { action ->
            when (action.pullEdge) {
                QMUIPullLayout.PULL_EDGE_TOP -> {
                    viewModel.page = 1
                    viewModel.loadData {
                        binding.content.finishActionRun(action)
                    }
                }
                QMUIPullLayout.PULL_EDGE_BOTTOM -> {
                    viewModel.loadData {
                        binding.content.finishActionRun(action)
                    }
                }
            }
        }
    }

}