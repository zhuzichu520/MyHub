package com.zhuzichu.android.login

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
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
    }

}