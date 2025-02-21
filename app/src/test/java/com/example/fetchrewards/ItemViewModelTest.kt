package com.example.fetchrewards

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fetchrewards.data.repository.ItemRepository
import com.example.fetchrewards.data.vo.GroupedItem
import com.example.fetchrewards.data.vo.ListItem
import com.example.fetchrewards.ui.ItemViewModel
import com.example.fetchrewards.utils.MainCoroutineRule
import com.example.fetchrewards.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
@ExperimentalCoroutinesApi
class ItemViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ItemViewModel
    private val repository: ItemRepository = mockk()

    @Before
    fun setup() {
        viewModel = ItemViewModel(repository)
    }
    @Test
    fun fetchData() = runTest {
        val mockItems = listOf(
            ListItem(1, 1, "Item A"),
            ListItem(2, 1, "Item B"),
            ListItem(3, 2, ""),
            ListItem(4, 2, "Item C")
        )

        coEvery { repository.getListItems() } returns mockItems

        viewModel.fetchData()

        val expectedGroups = listOf(
            GroupedItem(1, listOf(ListItem(1, 1, "Item A"), ListItem(2, 1, "Item B"))),
            GroupedItem(2, listOf(ListItem(4, 2, "Item C")))
        )

        assertEquals(expectedGroups, viewModel.groupedItems.getOrAwaitValue())
    }
}