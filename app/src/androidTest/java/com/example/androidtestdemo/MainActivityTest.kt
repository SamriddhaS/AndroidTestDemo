package com.example.androidtestdemo

import android.content.Intent
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.rules.activityScenarioRule
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test


class MainActivityTest{

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun testNexBtn_checkCorrectQuote(){
        onView(withId(R.id.btn_next)).perform(
            click()
        )
        onView(withId(R.id.btn_next)).perform(
            click()
        )
        onView(withId(R.id.btn_previous)).perform(
            click()
        )

        onView(withId(R.id.quoteAuthor)).check(
            matches(withText("Napoleon Hill"))
        )
    }

    @Test
    fun testShareButton_shouldOpenShareIntent(){

        Intents.init()
        val expected = allOf(hasAction(Intent.ACTION_SEND))
        onView(withId(R.id.floatingActionButton)).perform(
            click()
        )
        intended(expected)
        Intents.release()
    }
}