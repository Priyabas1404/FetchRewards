package com.example.fetchrewards.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewards.data.repository.ItemRepository
import com.example.fetchrewards.data.vo.GroupedItem
import com.example.fetchrewards.data.vo.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val repository: ItemRepository) : ViewModel() {
    private val _groupedItems = MutableLiveData<List<GroupedItem>>()
    val groupedItems: LiveData<List<GroupedItem>> get() = _groupedItems

    fun fetchData() {
        viewModelScope.launch {
            try {
                val items = repository.getListItems()

                val filteredItems = items.filter { !it.name.isNullOrBlank() }

                val sortedItems = filteredItems.sortedWith(compareBy({ it.listId }, { it.name }))

                val groupedItems = sortedItems
                    .groupBy { it.listId }
                    .map { GroupedItem(it.key, it.value) }

                _groupedItems.postValue(groupedItems)

            } catch (e: Exception) {
                Log.e("ItemViewModel", "Error fetching data: ${e.message}")
            }
        }
    }
}