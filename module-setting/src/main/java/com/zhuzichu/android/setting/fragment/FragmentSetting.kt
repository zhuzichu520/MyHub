package com.zhuzichu.android.setting.fragment

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.libs.tool.setOnClickListener
import com.hiwitech.android.mvvm.base.ArgDefault
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import com.zhuzichu.android.setting.R
import com.zhuzichu.android.setting.BR
import com.zhuzichu.android.setting.databinding.FragmentSettingBinding
import com.zhuzichu.android.setting.viewmodel.ViewModelSetting
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.entity.arg.ArgMain
import com.zhuzichu.android.shared.entity.enumeration.EnumMainType
import com.zhuzichu.android.shared.route.RoutePath
import kotlinx.android.synthetic.main.fragment_setting.*

@Route(path = RoutePath.Setting.FRAGMENT_SETTING_MAIN)
class FragmentSetting : FragmentBase<FragmentSettingBinding, ViewModelSetting, ArgDefault>(),
    View.OnClickListener {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_setting

    override fun initView() {
        super.initView()
        initTopBar()
        QMUIGroupListView.newSection(context)
            .setTitle(String())
            .addItemView(setting_list.createItemView(resources.getString(R.string.about)).apply {
                accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
            }) {
                navigate(RoutePath.Setting.FRAGMENT_SETTING_ABOUT)
            }
            .setMiddleSeparatorInset(QMUIDisplayHelper.dp2px(context, 16), 0)
            .addTo(setting_list)
    }

    override fun initListener() {
        super.initListener()
        setOnClickListener(this, logout)
    }

    private fun initTopBar() {
        topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
    }

    override fun onClick(view: View) {
        when (view) {
            logout -> {
                navigate(RoutePath.Main.ACTIVITY_MAIN_MAIN, ArgMain(EnumMainType.LOGOUT))
            }
            else -> {
            }
        }
    }

}
