package com.bignerdranch.android.flashcardmath

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlashcardActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<FlashcardActivity> =
        ActivityScenarioRule(FlashcardActivity::class.java)

    @Test
    fun clickGenerateNumButton_checkOperandText() {
        // Click the "generateNum" button
        onView(withId(R.id.generateNum)).perform(click())

        // Check if the "firstOperand" TextView is not empty
        onView(withId(R.id.firstOperand)).check(matches(not(withText(""))))
    }
}