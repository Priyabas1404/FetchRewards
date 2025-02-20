package com.example.fetchrewards.ui

import androidx.lifecycle.ViewModel
import com.example.fetchrewards.data.repository.ItemRepository
import com.example.fetchrewards.data.vo.DataList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val repository: ItemRepository) : ViewModel() {
    private val _items = MutableStateFlow<Map<Int, List<DataList>>>(emptyMap())
    val items: StateFlow<Map<Int, List<DataList>>> get() = _items

    private suspend fun fetchItems() {
        try {
            val rawItems = repository.getListItems()

            // Filter out items with null or blank name
            val filteredItems = rawItems.filter { it.name != null }

            // Sort by listId and name
            val sortedItems = filteredItems.sortedWith(compareBy({ it.listId }, { it.name }))

            // Group by listId
            val groupedItems = sortedItems.groupBy { it.listId }

            _items.value = groupedItems
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}