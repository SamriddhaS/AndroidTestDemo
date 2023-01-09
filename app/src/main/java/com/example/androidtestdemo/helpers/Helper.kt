package com.example.androidtestdemo.helpers

class Helper {

    fun isPalindrome(input:String):Boolean{
        input.forEachIndexed { index, _ ->
            if (input[index].lowercase()!=input[(input.length-1)-index].lowercase()) return false
        }
        return true
    }
}