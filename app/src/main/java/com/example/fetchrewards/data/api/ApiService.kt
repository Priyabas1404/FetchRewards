package com.example.fetchrewards.data.api

import com.example.fetchrewards.data.vo.DataList
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getListItems(): List<DataList>
}