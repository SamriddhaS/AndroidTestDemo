package com.example.androidtestdemo.utilsTest

import com.example.androidtestdemo.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class UtilsTestCases {

    /*
    * We don't have a main dispatcher available inside test class so we cant
    * test those functions which use a main dispatcher inside them. As a workaround we have a testDispatcher class,
    * whenever we need to test some fun which has main dispatcher in it we will assign our testDispatcher to test
    * that function inside our test class.
    *
    * We also need to make sure to reset the dispatcher inside @After class once the test case is complete.
    *
    * When testing we don't want multiple threads to be there for that reason this "StandardTestDispatcher" class is
    * created.This class tries to manage the coroutine test case in a single thread so that the tests can be reliablelly executed.
    * */
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp(){
        // Whenever we get a request for main dispatcher instead use this testDispatcher to execute the function.
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun test_get_user_data(){
        val util = Utils()
        /*
        * This will execute the suspend function but will block the thread until the suspend fun gets executed.
        * This is not ideal and not the best practice to follow because it will block the thread for sometime.
        * So inteade we can use runTest function to launch the coroutine see test_get_user_data_2() function to get an example.
        * */
        runBlocking {
            util.getUserData()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_get_user_data_2(){
        val util = Utils()
        /*
        * This runTest scope is specially made for testing coroutines. This will optimise our suspend function best way possible
        * and avoid delays and similar things to get the test complete in much quicker time.
        * */
        runTest {
            util.getUserData()
        }
    }

    @Test
    fun test_a_function_with_main_dispatcher(){
        val util = Utils()
        runTest {
            util.getUserDataMainDispatcher()
        }
    }
}