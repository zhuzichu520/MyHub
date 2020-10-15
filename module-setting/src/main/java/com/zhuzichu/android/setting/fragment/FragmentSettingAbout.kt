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
import com.zhuzichu.android.shared.ext.toStringByResId
import com.zhuzichu.android.shared.route.RoutePath
import kotlinx.android.synthetic.main.fragment_setting.topbar
import kotlinx.android.synthetic.main.fragment_setting_about.*

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
        val titleGithub = R.string.github.toStringByResId(requireContext())
        val itemGithub = about_list.createItemView(titleGithub)
        QMUIGroupListView.newSection(context)
            .addItemView(itemGithub) {
                val url = "https://github.com/Tencent/QMUI_Android"
                navigate(
                    RoutePath.Web.ACTIVITY_WEB_MAIN,
                    ArgWeb(url, titleGithub)
                )
            }
            .addTo(about_list)
    }

    private fun initTopBar() {
        topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
    }

}
