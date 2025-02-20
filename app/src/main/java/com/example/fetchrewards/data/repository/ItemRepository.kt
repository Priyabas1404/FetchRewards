package com.example.fetchrewards.data.repository

import com.example.fetchrewards.data.api.ApiService
import com.example.fetchrewards.data.vo.ListItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getListItems(): List<ListItem> {
        return try {
            apiService.getListItems()
        } catch (e: Exception) {
            emptyList()
        }
    }
}