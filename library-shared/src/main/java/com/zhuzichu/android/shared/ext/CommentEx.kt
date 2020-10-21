package com.zhuzichu.android.shared.ext

import android.os.Build
import android.text.Html
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import com.hiwitech.android.mvvm.Mvvm
import com.hiwitech.android.mvvm.base.BaseArg
import com.hiwitech.android.mvvm.base.BaseFragment
import com.hiwitech.android.mvvm.base.BaseViewModel

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/20 3:24 PM
 * since: v 1.0.0
 */
fun <TBinding : ViewDataBinding, TViewModel : BaseViewModel<TArg>, TArg : BaseArg> BaseFragment<TBinding, TViewModel, TArg>.setArg(
    arg: BaseArg
): BaseFragment<TBinding, TViewModel, TArg> {
    this.arguments = bundleOf(Mvvm.KEY_ARG to arg)
    return this
}