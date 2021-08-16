package com.bitpanda.developertest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bitpanda.developertest.ui.MainActivity
import com.bitpanda.developertest.ui.price.PriceItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {

    @Rule
    @JvmField
    val mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Rule
    @JvmField
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkViewsDisplay() {
        onView(withId(R.id.walletsFragment))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testWalletsNumberCollection() {
        onView(withId(R.id.recyclerView))
            .check(RecyclerViewItemCountAssertion(8))
    }

    @Test
    fun testCurrencySymbolCollection() {
        onView(withId(R.id.recyclerView))
            .check(matches(hasDescendant(withText("EUR"))))
    }

    @Test
    fun testCurrencyBalanceCollection() {
        onView(withId(R.id.recyclerView))
            .check(matches(hasDescendant(withText("400.0"))))
    }

    @Test
    fun testChangeCurrencyCollection() {
        onView(withId(R.id.fab))
            .perform(click())

        onView(withId(R.id.recyclerView))
            .check(matches(hasDescendant(withText("BTC"))))
    }

    @Test
    fun testOpenPriceViewDetail() {
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        composeTestRule.setContent {
            PriceItem(price = "0.0300€")
        }

        composeTestRule.onNodeWithText("0.0300€").assertIsDisplayed()

    }

}