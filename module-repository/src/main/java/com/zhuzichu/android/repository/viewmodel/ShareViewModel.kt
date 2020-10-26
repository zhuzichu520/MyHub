package com.zhuzichu.android.repository.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhuzichu.android.shared.entity.arg.ArgRepository
import com.zhuzichu.android.shared.entity.bean.BeanRepository

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/20 3:32 PM
 * since: v 1.0.0
 */
class ShareViewModel : ViewModel() {

    val title = MutableLiveData<String>()

    val repository = MutableLiveData<ArgRepository>()
}