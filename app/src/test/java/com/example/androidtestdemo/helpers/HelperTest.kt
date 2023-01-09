package com.example.androidtestdemo.helpers

import org.junit.Test
import org.junit.Assert.*


class HelperTest {

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
        // Arrange
        val helper = Helper()
        // Act
        val result = helper.isPalindrome("Level")
        // Assert
        assertEquals(true,result)
    }
}