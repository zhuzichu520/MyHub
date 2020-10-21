package com.zhuzichu.android.search.fragment


import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.zhuzichu.android.search.R
import com.zhuzichu.android.search.BR
import com.zhuzichu.android.search.databinding.FragmentSearchBinding
import com.zhuzichu.android.search.viewmodel.ViewModelSearch
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath
import kotlinx.android.synthetic.main.fragment_search.*

@Route(path = RoutePath.Search.FRAGMENT_SEARCH_MAIN)
class FragmentSearch : FragmentBase<FragmentSearchBinding, ViewModelSearch, ArgDefault>() {


    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_search

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
