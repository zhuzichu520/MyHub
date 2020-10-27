package com.zhuzichu.android.me.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hiwitech.android.mvvm.base.ArgDefault
import com.hiwitech.android.mvvm.ext.createCommand
import com.rxjava.rxlife.life
import com.zhuzichu.android.shared.base.ViewModelBase
import com.zhuzichu.android.shared.domain.UseCaseGetPerson
import com.zhuzichu.android.shared.entity.bean.BeanUser
import com.zhuzichu.android.shared.entity.enumeration.EnumEmptyStatus
import com.zhuzichu.android.shared.tools.ParseDateFormat

class ViewModelMe : ViewModelBase<ArgDefault>() {

    val user = MutableLiveData<BeanUser>()

    val emptyStatus = MutableLiveData(EnumEmptyStatus.LOADING)

    val createTime: LiveData<String> = Transformations.map(user) {
        ParseDateFormat.getTimeAgo(it.createdAt).toString()
    }

    private val useCaseGetUser by lazy {
        UseCaseGetPerson()
    }

    override fun initLazyData() {
        loadUser()
    }

    val emptyErrorCommand = createCommand {
        emptyStatus.value = EnumEmptyStatus.LOADING
        loadUser()
    }

    private fun loadUser() {
        useCaseGetUser.execute(Unit)
            .life(this)
            .subscribe(
                {
                    user.value = it
                    emptyStatus.value = EnumEmptyStatus.SUCCESS
                },
                {
                    emptyStatus.value = EnumEmptyStatus.ERROR
                }
            )
    }

}
