package com.zhuzichu.android.me.fragment

import androidx.core.content.ContextCompat.getDrawable
import com.alibaba.android.arouter.facade.annotation.Route
import com.hiwitech.android.mvvm.base.ArgDefault
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import com.zhuzichu.android.me.R
import com.zhuzichu.android.me.BR
import com.zhuzichu.android.me.databinding.FragmentMeBinding
import com.zhuzichu.android.me.viewmodel.ViewModelMe
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.route.RoutePath

@Route(path = RoutePath.Me.FRAGMENT_ME_MAIN)
class FragmentMe : FragmentBase<FragmentMeBinding, ViewModelMe, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_me

    override fun initView() {
        super.initView()
        initTopBar()
        initGroup()
    }

    private fun initGroup() {
        QMUIGroupListView.newSection(context)
            .setTitle(String())
            .addItemView(
                binding.group.createItemView(
                    getDrawable(requireContext(), R.drawable.ic_trace),
                    getString(R.string.trace),
                    null,
                    QMUICommonListItemView.HORIZONTAL,
                    QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
                )
            ) {
                navigate(RoutePath.Me.FRAGMENT_ME_TRACE)
            }
            .setMiddleSeparatorInset(QMUIDisplayHelper.dp2px(context, 16), 0)
            .addTo(binding.group)
    }

    private fun initTopBar() {
        binding.topbar.addRightImageButton(
            R.drawable.ic_topbar_setting,
            R.id.topbar_right_setting_button
        ).setOnClickListener {
            navigate(RoutePath.Setting.ACTIVITY_SETTING_MAIN)
        }
    }
}
