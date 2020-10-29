package com.zhuzichu.android.myhub

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.zhuzichu.android.login.ActivityLogin
import com.zhuzichu.android.shared.storage.AppStorage
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws


/**
 * desc
 * author: 朱子楚
 * time: 2020/10/29 9:28 AM
 * since: v 1.0.0
 */
@RunWith(AndroidJUnit4::class)
class ActivityLoginTest {

    @Rule
    @JvmField
    val loginRule: ActivityTestRule<ActivityLogin> =
        ActivityTestRule(ActivityLogin::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() {

    }

    @Test
    @Throws(Exception::class)
    fun loginActivityTest() {
        //清除登录记录
        AppStorage.token = null

        //输入用户名
        onView(withId(R.id.username)).perform(
            clearText(),
            replaceText("zhuzichu520"),
            closeSoftKeyboard()
        )

        //输入密码
        onView(withId(R.id.password)).perform(
            clearText(),
            replaceText("zhuzichu123"),
            closeSoftKeyboard()
        )
        //点击登录
        onView(withId(R.id.login)).perform(click())
    }

    @After
    @Throws(Exception::class)
    fun release() {
        //是否资源，清除flag
    }
}