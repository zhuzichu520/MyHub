package com.zhuzichu.android.shared.domain

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.bean.BeanFile
import com.zhuzichu.android.shared.entity.enumeration.EnumFileType
import com.zhuzichu.android.shared.entity.param.ParamFileList
import com.zhuzichu.android.shared.ext.bindToSchedulers
import io.reactivex.rxjava3.core.Observable
import rxhttp.RxHttp

/**
 * desc 获取项目文件列表
 * author: 朱子楚
 * time: 2020/9/16 1:13 PM
 * since: v 1.0.0
 */
class UseCaseGetRepoFileList : UseCase<ParamFileList, Observable<List<BeanFile>>>() {

    override fun execute(parameters: ParamFileList): Observable<List<BeanFile>> {
        return RxHttp
            .get(
                "/repos/%s/%s/contents/%s",
                parameters.owner,
                parameters.repo,
                parameters.path
            )
            .add("ref", parameters.owner)
            .asList(BeanFile::class.java)
            .map {
                it.sortWith { o1, o2 ->
                    if (o1.type != o2.type) {
                        if (o1.type == EnumFileType.DIR) -1 else 1
                    } else 0
                }
                it
            }
            .bindToSchedulers()
    }

}