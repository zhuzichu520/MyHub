package com.zhuzichu.android.search.fragment

import android.view.View
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.recyclical.ViewHolder
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.alibaba.android.arouter.facade.annotation.Route
import com.jakewharton.rxbinding4.viewpager.pageSelections
import com.qmuiteam.qmui.skin.QMUISkinManager
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.popup.QMUIPopup
import com.qmuiteam.qmui.widget.popup.QMUIPopups
import com.zhuzichu.android.search.BR
import com.zhuzichu.android.search.R
import com.zhuzichu.android.search.databinding.FragmentSearchResultBinding
import com.zhuzichu.android.search.viewmodel.ShareViewModel
import com.zhuzichu.android.search.viewmodel.ViewModelSearchResult
import com.zhuzichu.android.shared.base.DefaultIntFragmentPagerAdapter
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.entity.arg.ArgSearch
import com.zhuzichu.android.shared.ext.setArg
import com.zhuzichu.android.shared.route.RoutePath

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/26 2:27 PM
 * since: v 1.0.0
 */

@Route(path = RoutePath.Search.FRAGMENT_SEARCH_RESULT)
class FragmentSearchResult :
    FragmentBase<FragmentSearchResultBinding, ViewModelSearchResult, ArgSearch>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_search_result

    private val share by activityViewModels<ShareViewModel>()

    private var normalPopup: QMUIPopup? = null

    private val titles = listOf(
        R.string.repositories,
        R.string.users
    )

    override fun initView() {
        super.initView()
        initTopBar()
        initTabAndViewPager()
    }

    private fun initTabAndViewPager() {
        val builder = binding.tab.tabBuilder()
        repeat(titles.size) {
            binding.tab.addTab(builder.build(context))
        }
        binding.content.adapter = DefaultIntFragmentPagerAdapter(
            childFragmentManager,
            titles = titles,
            list = listOf(
                FragmentSearchRepositoriess().setArg(arg),
                FragmentSearchUsers().setArg(arg)
            )
        )
        binding.tab.setupWithViewPager(binding.content, true)
    }

    override fun initListener() {
        super.initListener()
        binding.content.pageSelections().subscribe {
            share.position.value = it
        }
    }

    override fun initLazyData() {
        viewModel.title.value = arg.keyword
    }

    private fun initTopBar() {
        binding.topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
        binding.topbar.addRightImageButton(R.drawable.ic_topbar_menu, R.id.topbar_right_menu_button)
            .setOnClickListener {
                showPopup(it)
            }
    }

    override fun initViewObservable() {
        super.initViewObservable()
        share.position.observe(viewLifecycleOwner) {
            val searchMode = share.getSearchMode() ?: return@observe
            binding.topbar.setSubTitle(searchMode.textId)
        }
    }

    private fun showPopup(anchor: View) {
        val data = share.getDataSource() ?: return
        val recycler = RecyclerView(requireContext())
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.setup {
            withDataSource(data)
            withItem<ShareViewModel.SearchMode, SearchModeHolder>(R.layout.item_search_mode) {
                onBind(::SearchModeHolder) { _, item ->
                    name.setText(item.textId)
                }
                onClick {
                    when (share.updateSearchMode(this.item)) {
                        0 -> {
                            share.onRepositoriesSearchChangeEvent.call()
                        }
                        1 -> {
                            share.onUsersSearchChangeEvent.call()
                        }
                    }
                    normalPopup?.dismiss()
                }
            }
        }
        normalPopup = QMUIPopups.popup(context, QMUIDisplayHelper.dp2px(context, 180))
            .preferredDirection(QMUIPopup.DIRECTION_TOP)
            .view(recycler)
            .skinManager(QMUISkinManager.defaultInstance(context))
            .offsetYIfTop(QMUIDisplayHelper.dp2px(context, 5))
            .shadow(true)
            .arrow(true)
            .animStyle(QMUIPopup.ANIM_AUTO)
            .show(anchor)
    }

    class SearchModeHolder(itemView: View) : ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.text)
    }

}