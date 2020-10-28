package com.zhuzichu.android.myhub

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val packageName = "com.zhuzichu.android.myhub"

    private lateinit var context: Context


    @Test
    fun useAppContext() {
        // Context of the app under test.
        context = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals(packageName, context.packageName)
        val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        uiDevice.pressHome()
        startApp()
    }

    private fun startApp() {
        val intent = Intent()
        intent.component = ComponentName(packageName, "$packageName.ActivityMain")
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}