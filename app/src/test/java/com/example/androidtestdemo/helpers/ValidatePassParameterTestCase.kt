package com.example.androidtestdemo.helpers

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ValidatePassParameterTestCase(private val input:String, private val expected:Boolean) {

    @Test
    fun isValidPassword() {
        val helper = Helper()
        val result = helper.validatePassword(input)
        assertEquals(expected,result)
    }

    companion object{
        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} is valid : {1}")
        fun data():List<Array<Any>>{
            return listOf(
                arrayOf("",false),
                arrayOf("123",false),
                arrayOf("12345678",true),
                arrayOf("null",false)
            )
        }
    }
}