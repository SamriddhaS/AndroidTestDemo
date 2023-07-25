package com.example.androidtestdemo.utilsTest

import com.example.androidtestdemo.utils.Helper
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before


class HelperTest {

    private lateinit var helper: Helper

    /*
    * This function will execute before every test case.
    * As we have annotated with @Before
    * */
    @Before
    fun beforeTestCase(){
        println("Execute before every test case.")
        helper = Helper()
    }

    /*
    * This function will execute after every test case.
    * As we have annotated with @After
    * */
    @After
    fun afterTestCase(){
        println("Execute after every test case.")
    }

    @Test
    fun isPalindrome_inputString_palindrome_expectedFalse() {
        // Arrange
        val helper = Helper()
        // Act
        val result = helper.isPalindrome("Palindrome")
        // Assert
        assertEquals(false,result)
    }

    @Test
    fun isPalindrome_inputString_level_expectedTrue() {
        // Act
        val result = helper.isPalindrome("Level")
        // Assert
        assertEquals(true,result)
    }
}