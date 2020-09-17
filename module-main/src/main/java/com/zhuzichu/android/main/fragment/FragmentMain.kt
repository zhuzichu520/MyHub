package com.zhuzichu.android.main.fragment

import android.graphics.Typeface
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hiwitech.android.libs.tool.toCast
import com.hiwitech.android.mvvm.base.ArgDefault
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.tab.QMUITabBuilder
import com.zhuzichu.android.main.R
import com.zhuzichu.android.main.BR
import com.zhuzichu.android.main.databinding.FragmentMainBinding
import com.zhuzichu.android.main.viewmodel.ViewModelMain
import com.zhuzichu.android.shared.base.DefaultIntFragmentPagerAdapter
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.ext.toDrawableByResId
import com.zhuzichu.android.shared.ext.toStringByResId
import com.zhuzichu.android.shared.route.RoutePath
import kotlinx.android.synthetic.main.fragment_main.*

@Route(path = RoutePath.Main.FRAGMENT_MAIN_MAIN)
class FragmentMain : FragmentBase<FragmentMainBinding, ViewModelMain, ArgDefault>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_main

    override fun initView() {
        super.initView()
        val fragments = listOf<Fragment>(
            ARouter.getInstance().build(RoutePath.Home.FRAGMENT_HOME_MAIN)
                .navigation().toCast(),
            ARouter.getInstance().build(RoutePath.Me.FRAGMENT_ME_MAIN)
                .navigation().toCast()
        )
        initTabs()
        pager.offscreenPageLimit = fragments.size
        pager.adapter = DefaultIntFragmentPagerAdapter(parentFragmentManager, fragments)
        tabs.setupWithViewPager(pager, false)
    }

    private fun initTabs() {
        val builder: QMUITabBuilder = tabs.tabBuilder()
        builder.setTypeface(null, Typeface.DEFAULT_BOLD)
        builder.setSelectedIconScale(1.2f)
            .setTextSize(
                QMUIDisplayHelper.sp2px(context, 13),
                QMUIDisplayHelper.sp2px(context, 15)
            )
            .setDynamicChangeIconColor(false)
        val demo = builder
            .setNormalDrawable(R.drawable.ic_main_home.toDrawableByResId())
            .setSelectedDrawable(R.drawable.ic_main_home.toDrawableByResId())
            .setText(R.string.home.toStringByResId())
            .build(context)
        val home = builder
            .setNormalDrawable(R.drawable.ic_main_me.toDrawableByResId())
            .setSelectedDrawable(R.drawable.ic_main_me.toDrawableByResId())
            .setText(R.string.me.toStringByResId())
            .build(context)
        tabs.addTab(demo)
            .addTab(home)
    }
}
