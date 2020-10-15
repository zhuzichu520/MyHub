package com.zhuzichu.android.shared.domain.home

import com.hiwitech.android.mvvm.domain.UseCase
import com.zhuzichu.android.shared.entity.bean.BeanRepository
import com.zhuzichu.android.shared.entity.param.ParamGetReadme
import com.zhuzichu.android.shared.entity.param.ParamGetRepos
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.http.entity.PageList
import io.reactivex.rxjava3.core.Observable
import org.jsoup.Jsoup
import rxhttp.RxHttp


class UseCaseGetReadme : UseCase<ParamGetReadme, Observable<String>>() {

    override fun execute(parameters: ParamGetReadme): Observable<String> {
        return RxHttp.get("/%s/%s", parameters.login, parameters.name)
            .setDomainToHtmlIfAbsent()
            .asString()
            .map {
                getReadmeFromHtml(it)
            }
            .bindToSchedulers()
    }

    private fun getReadmeFromHtml(str: String): String {
        val readme = Jsoup.parse(str).getElementById("readme")
        return readme?.outerHtml() ?: "No Readme"
    }


}