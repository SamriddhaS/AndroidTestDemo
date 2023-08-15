package com.example.androidtestdemo.repo

class Repository {

    fun userLogin(name:String,password:String): LOGIN_STATUS {
        return when{
            name!="Samriddha" -> LOGIN_STATUS.INVALID_USER_NAME
            password!="123" -> LOGIN_STATUS.INVALID_PASSWORD
            else -> LOGIN_STATUS.LOGIN_SUCCESS
        }
    }
}

enum class LOGIN_STATUS {
    LOGIN_SUCCESS,
    INVALID_USER_NAME,
    INVALID_PASSWORD,
}