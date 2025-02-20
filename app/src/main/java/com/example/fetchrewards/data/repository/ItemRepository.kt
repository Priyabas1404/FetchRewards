package com.example.fetchrewards.data.repository

import com.example.fetchrewards.data.api.ApiService
import com.example.fetchrewards.data.vo.DataList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getListItems(): List<DataList> {
        return try {
            apiService.getListItems()
        } catch (e: Exception) {
            emptyList()
        }
    }
}