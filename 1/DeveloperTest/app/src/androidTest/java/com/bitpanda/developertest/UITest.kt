package com.bitpanda.developertest

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.bitpanda.developertest.repository.Repository
import com.bitpanda.developertest.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {

    @Rule
    @JvmField
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var repository: Repository

    @Before
    fun init() {
        mActivityTestRule.activity
            .supportFragmentManager.beginTransaction()

        repository = Repository()

        mActivityTestRule.restartActivity()
    }

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

        onView(withId(R.id.priceFragment))
            .check(matches(hasDescendant(withText("0.0300€"))))
    }

    private inline fun <reified T : Activity> ActivityTestRule<T>.restartActivity() {
        finishActivity()
        launchActivity(Intent(InstrumentationRegistry.getTargetContext(), T::class.java))
    }

}