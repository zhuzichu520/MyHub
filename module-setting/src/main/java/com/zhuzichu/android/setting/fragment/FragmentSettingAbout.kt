package com.zhuzichu.android.setting.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.libs.tool.getVersionName
import com.hiwitech.android.mvvm.base.ArgDefault
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import com.zhuzichu.android.setting.BR
import com.zhuzichu.android.setting.R
import com.zhuzichu.android.setting.databinding.FragmentSettingAboutBinding
import com.zhuzichu.android.setting.viewmodel.ViewModelSettingAbout
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.entity.arg.ArgWeb
import com.zhuzichu.android.shared.route.RoutePath

@Route(path = RoutePath.Setting.FRAGMENT_SETTING_ABOUT)
class FragmentSettingAbout :
    FragmentBase<FragmentSettingAboutBinding, ViewModelSettingAbout, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_setting_about

    override fun initView() {
        super.initView()
        initTopBar()
        viewModel.name.value = "MyHub"
        viewModel.version.value = requireContext().getVersionName()

        //github
        val titleGithub = getString(R.string.github)
        val itemGithub = binding.aboutList.createItemView(titleGithub)
        QMUIGroupListView.newSection(context)
            .addItemView(itemGithub) {
                val url = "https://github.com/zhuzichu520/MyHub"
                navigate(
                    RoutePath.Web.ACTIVITY_WEB_MAIN,
                    ArgWeb(url, titleGithub)
                )
            }
            .addTo(binding.aboutList)
    }

    private fun initTopBar() {
        binding.topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
    }

}
