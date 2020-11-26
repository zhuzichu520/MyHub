package com.zhuzichu.android.search.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.search.R
import com.zhuzichu.android.search.BR
import com.zhuzichu.android.search.databinding.FragmentSearchBinding
import com.zhuzichu.android.search.viewmodel.ViewModelSearch
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath

@Route(path = RoutePath.Search.FRAGMENT_SEARCH_MAIN)
class FragmentSearch : FragmentBase<FragmentSearchBinding, ViewModelSearch, ArgDefault>() {


    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_search

    override fun initView() {
        super.initView()
        initTopBar()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager = FlexboxLayoutManager(context).apply {
            justifyContent = JustifyContent.FLEX_START
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
    }

    private fun initTopBar() {
        binding.topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
    }

}
