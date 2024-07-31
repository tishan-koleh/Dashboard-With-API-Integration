package com.example.openinapp.network

import com.example.openinapp.network.response.DashboardResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/v1/dashboardNew")
    fun getDashboardData(): Call<DashboardResponse>
}