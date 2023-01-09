package com.example.androidtestdemo.helpers

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class ParameterTestCase(private val input:String, private val expected:Boolean) {

    @Test
    fun isPalindrome() {
        val helper = Helper()
        val result = helper.isPalindrome(input = input)
        assertEquals(expected,result)
    }

    companion object{
        @JvmStatic
        @Parameterized.Parameters(name = "{index} : {0} is Palindrome {1}")
        fun data():List<Array<Any>>{
            return listOf(
                arrayOf("input",false),
                arrayOf("Palindrome",false),
                arrayOf("level",true),
                arrayOf("a",true)
            )
        }
    }
}