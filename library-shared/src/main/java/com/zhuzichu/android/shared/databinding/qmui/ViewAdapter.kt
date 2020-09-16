package com.zhuzichu.android.shared.databinding.qmui

import androidx.databinding.BindingAdapter
import com.qmuiteam.qmui.widget.QMUITopBarLayout

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/15 1:52 PM
 * since: v 1.0.0
 */
@BindingAdapter(value = ["qmui_top_title"], requireAll = false)
fun bindQMUITopBarLayout(
    topBarLayout: QMUITopBarLayout,
    title: String?
) {
    title?.let {
        topBarLayout.setTitle(it)
    }
}
