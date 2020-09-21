package com.zhuzichu.android.me.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hiwitech.android.mvvm.base.ArgDefault
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.login.UseCaseGetUser
import com.zhuzichu.android.shared.entity.bean.BeanUser
import com.zhuzichu.android.shared.ext.autoLoading

class ViewModelMe : ViewModelBase<ArgDefault>() {

    val user = MutableLiveData<BeanUser>()

    private val useCaseGetUser by lazy {
        UseCaseGetUser()
    }

    override fun initLazyData() {
        loadUser()
    }

    private fun loadUser() {
        useCaseGetUser.execute(Unit)
            .autoLoading(this)
            .life(this)
            .subscribe {
                user.value = it
            }
    }

}
