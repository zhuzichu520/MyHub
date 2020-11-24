package com.zhuzichu.android.repository.fragment

import com.hiwitech.android.libs.internal.MainHandler
import com.zhuzichu.android.repository.R
import com.zhuzichu.android.repository.BR
import com.zhuzichu.android.repository.databinding.FragmentRepositoryFileBinding
import com.zhuzichu.android.repository.viewmodel.ItemViewModelRepositoryTab
import com.zhuzichu.android.repository.viewmodel.ViewModelRepositoryFile
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import kotlinx.android.synthetic.main.fragment_repository_file.*

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/20 3:01 PM
 * since: v 1.0.0
 */
class FragmentRepositoryFile :
    FragmentBase<FragmentRepositoryFileBinding, ViewModelRepositoryFile, ArgRepository>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repository_file

    override fun initLazyData() {
        super.initLazyData()
        viewModel.itemsTab.add(
            ItemViewModelRepositoryTab(viewModel, viewModel.bean, viewModel.onClickTabListener)
        )
        viewModel.loadData()
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.onTabAddEvent.observe(viewLifecycleOwner) {
            MainHandler.postDelayed {
                tab.smoothScrollToPosition(viewModel.itemsTab.size - 1)
            }
        }
    }
}