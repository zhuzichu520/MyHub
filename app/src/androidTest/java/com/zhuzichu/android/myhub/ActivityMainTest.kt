package com.zhuzichu.android.myhub

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * desc
 * author: 朱子楚
 * time: 2020/10/29 9:28 AM
 * since: v 1.0.0
 */
@RunWith(AndroidJUnit4::class)
class ActivityMainTest {

    @Rule
    @JvmField
    val mainRule: ActivityTestRule<ActivityMain> =
        ActivityTestRule(ActivityMain::class.java)

    @Test
    fun mainActivityTest() {

    }

}