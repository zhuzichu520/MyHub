package com.zhuzichu.android.shared.entity.enumeration

/**
 * desc
 * author: 朱子楚
 * time: 2020/11/3 11:32 AM
 * since: v 1.0.0
 */
enum class EnumFileAccept(val accept: String) {
    //Html数据
    HTML("Accept: application/vnd.github.html"),

    //原始数据
    RAW("Accept: application/vnd.github.VERSION.raw")
}