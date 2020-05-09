package com.joker.mvvm.kotlin

import com.joker.mvvm.kotlin.basemvvm.ext.no
import com.joker.mvvm.kotlin.basemvvm.ext.otherWise
import com.joker.mvvm.kotlin.basemvvm.ext.yes
import com.tencent.mmkv.MMKV
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testBooleanExt() {
        val result = true.yes {
            1
        }.otherWise {
            2
        }
        assertEquals(result,1)

        val result2 = false.no {
            1
        }.otherWise {
            2
        }

        assertEquals(result2,1)
    }



}
