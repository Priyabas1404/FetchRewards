package com.example.fetchrewards

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fetchrewards.data.vo.ListItem
import com.example.fetchrewards.ui.ItemViewModel
import com.example.fetchrewards.ui.MainActivity
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testRecyclerViewIsPopulated() {
        val mockItems = listOf(
            ListItem(id = 755, listId = 2, name = "Item 755"),
            ListItem(id = 203, listId = 2, name = "Item 203"),
            ListItem(id = 684, listId = 1, name = "Item 684")
        )

        activityRule.scenario.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(R.id.recyclerview)

            onView(withId(R.id.recyclerview)).check(matches(hasChildCount(mockItems.size)))
        }
    }

    @Test
    fun testFetchDataCalled() {
        val mockViewModel = mock(ItemViewModel::class.java)
        `when`(mockViewModel.fetchData()).thenReturn(Unit)

        activityRule.scenario.onActivity { activity ->
            activity.itemViewModel.fetchData()
        }
        verify(mockViewModel).fetchData()
    }
}