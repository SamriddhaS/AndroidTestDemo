package com.example.androidtestdemo.helpers

class Helper {

    fun isPalindrome(input:String):Boolean{
        input.forEachIndexed { index, _ ->
            if (input[index]!=input[(input.length-1)-index]) return false
        }
        return true
    }
}