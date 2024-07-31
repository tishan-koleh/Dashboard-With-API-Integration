package com.example.openinapp.model

import com.example.openinapp.network.ApiService
import com.example.openinapp.network.response.DashboardResponse
import retrofit2.Call

class Model {

    fun getDashboardData(apiService: ApiService): Call<DashboardResponse> {
        return apiService.getDashboardData()
    }

}