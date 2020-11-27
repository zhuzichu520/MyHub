package com.zhuzichu.android.shared.databinding.view

import android.view.View
import androidx.databinding.BindingAdapter
import com.hiwitech.android.mvvm.databinding.BindingCommand
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

/**
 * desc
 * author: 朱子楚
 * time: 2020/9/15 4:29 PM
 * since: v 1.0.0
 */

private fun <T> Observable<T>.isThrottleFirst(
    isThrottleFirst: Boolean
): Observable<T> {
    return this.compose {
        if (isThrottleFirst) {
            it.throttleFirst(150L, TimeUnit.MILLISECONDS)
        } else {
            it
        }
    }
}

@BindingAdapter(value = ["onClickCommand", "isThrottleFirst"], requireAll = false)
fun onClickCommand(view: View, clickCommand: BindingCommand<*>?, isThrottleFirst: Boolean?) {
    clickCommand?.apply {
        view.clicks().isThrottleFirst(isThrottleFirst ?: true).subscribe {
            execute()
        }
    }
}

/**
 * 设置控件Click监听事件
 *
 * @param onClickListener 监听实例
 * @param views           视图集合
 */
fun setOnClickDoubleListener(
    onClickListener: View.OnClickListener?,
    vararg views: View?,
    isThrottleFirst: Boolean? = null
) {
    if (views.isNotEmpty() && onClickListener != null) {
        for (view in views) {
            view?.let { v ->
                v.clicks().isThrottleFirst(isThrottleFirst ?: true).subscribe {
                    onClickListener.onClick(v)
                }
            }
        }
    }
}
