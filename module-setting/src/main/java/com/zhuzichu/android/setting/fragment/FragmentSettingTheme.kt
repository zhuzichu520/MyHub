package com.zhuzichu.android.setting.fragment

import android.widget.RadioButton
import androidx.appcompat.app.AppCompatDelegate.*
import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import com.zhuzichu.android.setting.BR
import com.zhuzichu.android.setting.R
import com.zhuzichu.android.setting.databinding.FragmentSettingThemeBinding
import com.zhuzichu.android.setting.viewmodel.ViewModelSettingTheme
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath
import com.zhuzichu.android.shared.skin.SkinManager
import com.zhuzichu.android.shared.storage.AppStorage

@Route(path = RoutePath.Setting.FRAGMENT_SETTING_THEME)
class FragmentSettingTheme :
    FragmentBase<FragmentSettingThemeBinding, ViewModelSettingTheme, ArgDefault>() {

    private val modes = listOf(
        MODE_NIGHT_NO,
        MODE_NIGHT_YES,
        MODE_NIGHT_FOLLOW_SYSTEM
    )

    private val strings = listOf(
        R.string.settings_theme_light,
        R.string.settings_theme_dark,
        R.string.settings_theme_system
    )

    private lateinit var buttons: List<RadioButton>

    private lateinit var itemViews: List<QMUICommonListItemView>

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_setting_theme

    override fun initView() {
        super.initView()
        initTopBar()
        initViewData()
        QMUIGroupListView.newSection(context)
            .setTitle(resources.getString(R.string.night_mode)).apply {
                itemViews.forEachIndexed { index, item ->
                    addItemView(item) {
                        AppStorage.uiMode = modes[index]
                        SkinManager.applyConfigurationChanged(requireContext().resources.configuration)
                        refreshButtons()
                    }
                }
            }
            .setMiddleSeparatorInset(QMUIDisplayHelper.dp2px(context, 16), 0)
            .addTo(binding.themeList)
    }

    fun refreshButtons() {
        modes.forEachIndexed { index, mode ->
            buttons[index].isChecked = mode == AppStorage.uiMode
        }
    }

    private fun initViewData() {
        buttons = modes.map {
            RadioButton(context).apply {
                isClickable = false
                isChecked = it == AppStorage.uiMode
            }
        }
        itemViews = modes.mapIndexed { index, _ ->
            binding.themeList.createItemView(resources.getString(strings[index])).apply {
                accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM
                addAccessoryCustomView(buttons[index])
            }
        }
    }

    private fun initTopBar() {
        binding.topbar.addLeftImageButton(R.drawable.ic_topbar_back, R.id.topbar_left_back_button)
            .setOnClickListener {
                back()
            }
    }

}
