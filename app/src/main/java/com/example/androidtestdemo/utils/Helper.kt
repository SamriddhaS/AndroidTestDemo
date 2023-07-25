package com.example.androidtestdemo.utils

class Helper {

    fun isPalindrome(input:String):Boolean{
        input.forEachIndexed { index, _ ->
            if (input[index].lowercase()!=input[(input.length-1)-index].lowercase()) return false
        }
        return true
    }

    fun validatePassword(password:String):Boolean{
        return when {
            password.isNullOrEmpty() -> false
            password.length<6||password.length>15 -> false
            else -> true
        }
    }

    fun reverseString(input: String):String{
        var result = ""
        for(i in input.length-1 downTo 0 ){
            val text = input[i]
            result+=text
        }
        return result
    }
}