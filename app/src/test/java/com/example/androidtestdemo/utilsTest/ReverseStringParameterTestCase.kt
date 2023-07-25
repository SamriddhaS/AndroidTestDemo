package com.example.androidtestdemo.utilsTest

import com.example.androidtestdemo.utils.Helper
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ReverseStringParameterTestCase(private val input:String, private val expected:String) {

    @Test
    fun isReversingString() {
        val helper = Helper()
        val result = helper.reverseString(input)
        assertEquals(expected,result)
    }

    companion object{
        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} after reverse : {1}")
        fun data():List<Array<Any>>{
            return listOf(
                arrayOf("",""),
                arrayOf("123","321"),
                arrayOf("abzs","szba"),
            )
        }
    }
}