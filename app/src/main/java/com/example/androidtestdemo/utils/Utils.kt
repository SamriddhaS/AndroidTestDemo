package com.example.androidtestdemo.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Utils() {

    suspend fun getUserData():String{
        delay(3000)
        return "User Details not found"
    }

    suspend fun getUserDataMainDispatcher():String{
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
        }
        return "User Details - Samriddha"
    }

    /*
    * For io dispatcher we don't have any work around available to write test cases for it
    * So instead of declaring the dispatcher inside the suspend function we will pass the dispatcher
    * as an depedency.
    * */
    suspend fun getUserDataIoDispatcher(dispatcher: CoroutineDispatcher):String{
        CoroutineScope(dispatcher).launch {
            delay(5000)
        }
        return "User Details - Samriddha"
    }

}