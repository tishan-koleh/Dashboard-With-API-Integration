package com.example.openinapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openinapp.model.Model
import com.example.openinapp.network.ApiService
import com.example.openinapp.network.response.DashboardResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val apiService: ApiService) : ViewModel() {

    private val model = Model()

    private val dashboardLiveData = MutableLiveData<DashboardResponse?>()
    val dashboardData: LiveData<DashboardResponse?>
        get() = dashboardLiveData

    private val loadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = loadingLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorLiveData

    fun getDashboardData() {
        loadingLiveData.postValue(true)
        val call = model.getDashboardData(apiService)
        call.enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if (response.isSuccessful) {
                    dashboardLiveData.postValue(response.body())
                } else {
                    errorLiveData.postValue("Error ${response.errorBody()}")
                }
                loadingLiveData.postValue(false)
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                errorLiveData.postValue("Error ${t.message}")
                loadingLiveData.postValue(false)
            }
        })
    }
}