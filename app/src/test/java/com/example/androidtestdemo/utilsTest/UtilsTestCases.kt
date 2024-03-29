package com.example.androidtestdemo.utilsTest

import com.example.androidtestdemo.testRules.CoroutineRule
import com.example.androidtestdemo.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UtilsTestCases {

    /*
    * Instead of using a local test dispatcher we can create a rule for that same work. So we can use
    * the same test dispatcher code in different classes without repeating the same code.
    * */
    @get:Rule
    val coroutineRule  = CoroutineRule()

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
            val result = util.getUserData()
            assertEquals(result,"User Details not found")
        }
    }

    @Test
    fun test_a_function_with_main_dispatcher(){
        val util = Utils()
        runTest {
            val result = util.getUserDataMainDispatcher()
            assertEquals(result,"User Details - Samriddha")
        }
    }

    /*
    * This test case here will not pass because testDispatcher only runs in a single thread. All of our coroutine gets
    * scheduled in that single thread only. Note that it gets scheduled for execution but doesn't gets executed until
    * we want to execute the coroutine. See the next test function to know how to execute it.
    * */
    @Test
    fun test_a_function_with_io_dispatcher(){
        val util = Utils()
        runTest {
            val result = util.getUserDataIoDispatcher(coroutineRule.testDispatcher)
            assertEquals(result,"coroutine executed")
        }
    }

    @Test
    fun test_a_function_with_io_dispatcher_will_execute_coroutine(){
        val util = Utils()
        runTest {
            val result = util.booleanValueInsideCoroutine(coroutineRule.testDispatcher)
            // by adding this line we are waiting till the coroutine is executed then checking the assert statement
            coroutineRule.testDispatcher.scheduler.advanceUntilIdle()
            assertEquals(true,util.myBool)
        }
    }


}