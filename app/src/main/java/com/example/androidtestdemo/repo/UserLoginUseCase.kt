package com.example.androidtestdemo.repo

class UserLoginUseCase(private val repository: Repository) {

    fun loginUserUsingNameAndPassword(name:String,password:String):Int{
        return when(repository.userLogin(name = name,password = password)){
            LOGIN_STATUS.INVALID_USER_NAME -> 1
            LOGIN_STATUS.INVALID_PASSWORD -> 2
            LOGIN_STATUS.LOGIN_SUCCESS -> 3
        }
    }
}