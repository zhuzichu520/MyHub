package com.zhuzichu.android.repository.fragment

import com.zhuzichu.android.repository.R
import com.zhuzichu.android.repository.BR
import com.zhuzichu.android.repository.databinding.FragmentRepositoryFileBinding
import com.zhuzichu.android.repository.viewmodel.ViewModelRepositoryFile
import com.zhuzichu.android.shared.base.FragmentBase
import com.zhuzichu.android.shared.entity.arg.ArgRepository

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/20 3:01 PM
 * since: v 1.0.0
 */
class FragmentRepositoryFile :
    FragmentBase<FragmentRepositoryFileBinding, ViewModelRepositoryFile, ArgRepository>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_repository_file

}