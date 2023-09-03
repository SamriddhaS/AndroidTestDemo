package com.example.androidtestdemo.testRules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class CoroutineRule:TestWatcher() {

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
    val testDispatcher = StandardTestDispatcher()

    /*
    * This is equivalent to our @Before annotated functions in our junit tests.
    * */
    override fun starting(description: Description) {
        super.starting(description)
        // Whenever we get a request for main dispatcher instead use this testDispatcher to execute the function.
        Dispatchers.setMain(testDispatcher)
    }

    /*
    * This is equivalent to our @After annotated functions in our junit tests.
    * */
    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}