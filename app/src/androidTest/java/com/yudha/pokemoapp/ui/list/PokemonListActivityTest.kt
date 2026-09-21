package com.yudha.pokemoapp.ui.list

import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yudha.pokemoapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonListActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(PokemonListActivity::class.java)

    @Test
    fun checkSearchVisibility() {
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFabFavoriteVisibility() {
        onView(withId(R.id.fabFavorite)).check(matches(isDisplayed()))
    }

    @Test
    fun performSearch() {
        onView(withId(R.id.searchView)).perform(click())
        onView(isAssignableFrom(SearchView.SearchAutoComplete::class.java))
            .perform(typeText("pikachu"), pressImeActionButton())
        
        // Note: In real world, we would use IdlingResource or Mock the API response
        // for more reliable UI testing.
    }
}
