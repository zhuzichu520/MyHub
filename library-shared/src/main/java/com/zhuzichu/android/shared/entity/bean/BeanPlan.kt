package com.zhuzichu.android.shared.entity.bean

import com.google.gson.annotations.SerializedName

data class BeanPlan(
    @SerializedName("collaborators")
    var collaborators: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("private_repos")
    var privateRepos: Int? = null,
    @SerializedName("space")
    var space: Int? = null
) {
    override fun toString(): String {
        return "BeanPlan(collaborators=$collaborators, name=$name, privateRepos=$privateRepos, space=$space)"
    }
}