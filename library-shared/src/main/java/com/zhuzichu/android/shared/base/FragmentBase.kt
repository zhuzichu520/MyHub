package com.zhuzichu.android.shared.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.hiwitech.android.mvvm.base.BaseArg
import com.hiwitech.android.mvvm.base.BaseFragment
import com.hiwitech.android.mvvm.base.BaseViewModel
import com.qmuiteam.qmui.arch.SwipeBackLayout
import com.qmuiteam.qmui.util.QMUIDisplayHelper

abstract class FragmentBase<TBinding : ViewDataBinding, TViewModel : BaseViewModel<TArg>, TArg : BaseArg> :
    BaseFragment<TBinding, TViewModel, TArg>() {

    private var bindId = -1

    override fun backViewInitOffset(context: Context?, dragDirection: Int, moveEdge: Int): Int {
        return if (moveEdge == SwipeBackLayout.EDGE_TOP || moveEdge == SwipeBackLayout.EDGE_BOTTOM) {
            0
        } else QMUIDisplayHelper.dp2px(context, 100)
    }

}
