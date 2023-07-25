package com.example.androidtestdemo

import android.content.Intent
import android.view.View
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Random


class MainActivityTest{

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()
    val activityScenarioRule1 = activityScenarioRule<NewActivity>()

    private var decorView: View? = null
    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            decorView = activity.window.decorView
        }
    }

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

    @Test
    fun testHitMeButton_shouldShowToastIfTextIsEmpty(){
        repeat(5) {
            onView(withId(R.id.edt_enter_input))
                .perform(typeText(""),ViewActions.closeSoftKeyboard())
            onView(withId(R.id.btn_hit)).perform(click())
            onView(withText("Enter something"))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()))
        }
    }
    @Test
    fun testHitMeButton_shouldPassTheTextEnteredAsStringToNextActivity(){
        repeat(5) {
            val textRandom = "Entered this :"+Random().nextDouble().toString()

            onView(withId(R.id.edt_enter_input))
                .perform(clearText(),typeText(textRandom),ViewActions.closeSoftKeyboard())
            onView(withId(R.id.btn_hit)).perform(click())
            onView(withId(R.id.text1)).check(matches(withText(textRandom)))
            pressBack()
        }
    }
}