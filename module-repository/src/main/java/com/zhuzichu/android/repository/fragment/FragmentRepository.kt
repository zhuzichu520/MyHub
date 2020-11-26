package com.zhuzichu.android.repository.fragment

import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhuzichu.android.repository.BR
import com.zhuzichu.android.repository.R
import com.zhuzichu.android.repository.databinding.FragmentRepositoryBinding
import com.zhuzichu.android.repository.viewmodel.ShareViewModel
import com.zhuzichu.android.repository.viewmodel.ViewModelRepository
import com.zhuzichu.android.shared.base.DefaultIntFragmentPagerAdapter
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.ext.setArg
import com.zhuzichu.android.shared.route.RoutePath

@Route(path = RoutePath.Repository.FRAGMENT_REPOSITORY_MAIN)
class FragmentRepository :
    FragmentBase<FragmentRepositoryBinding, ViewModelRepository, ArgRepository>() {

    private val titles = listOf(
        R.string.repository_info,
        R.string.repository_files,
        R.string.repository_commits,
        R.string.repository_activity
    )

    private val share by activityViewModels<ShareViewModel>()

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repository

    override fun initArgs(arg: ArgRepository) {
        super.initArgs(arg)
        share.repository.value = arg
    }

    override fun initView() {
        super.initView()
        initTabAndViewPager()
        initTopBar()
    }

    override fun initVariable() {
        super.initVariable()
        binding?.setVariable(BR.share, share)
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
                FragmentRepositoryInfo().setArg(arg),
                FragmentRepositoryFile().setArg(arg),
                FragmentRepositoryCommit().setArg(arg),
                FragmentRepositoryActivity().setArg(arg)
            )
        )
        binding.tab.setupWithViewPager(binding.content, true)
    }

    private fun initTopBar() {
        binding.topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
        share.title.value = arg.name
    }

}
