package com.alokomkar.btc

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
@SmallTest
class MainActivityTest {

    @Rule
    @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun fab_click_displays_progress() {
        onView(withId(R.id.fab))
            .perform(click())

        onView(withId(R.id.pbCurrentPrice))
            .check(matches(isDisplayed()))

        onView(withId(R.id.pbHistory))
            .check(matches(isDisplayed()))
    }


}