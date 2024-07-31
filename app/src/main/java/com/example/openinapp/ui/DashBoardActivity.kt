package com.example.openinapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openinapp.R
import com.example.openinapp.databinding.ActivityMainBinding
import com.example.openinapp.model.ClickData
import com.example.openinapp.model.convertToMonthlyClickData
import com.example.openinapp.network.ApiService
import com.example.openinapp.network.RetrofitHelper
import com.example.openinapp.network.response.DashboardResponse
import com.example.openinapp.viewModel.MainViewModel
import com.example.openinapp.viewModel.MainViewModelFactory
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var linktype = LINK_TYPE_TOP
    private var phoneNumber = "7909070754"
    private var email = "support@openinapp.com"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lineChart = findViewById<LineChart>(R.id.chart)

        val rawData = listOf(
            ClickData("2023-01-15T07:33:50.000Z", 50f),
            ClickData("2023-02-15T07:33:50.000Z", 80f),
            ClickData("2023-03-15T07:33:50.000Z", 60f),
            ClickData("2023-04-15T07:33:50.000Z", 90f),
            ClickData("2023-05-15T07:33:50.000Z", 70f),
            ClickData("2023-06-15T07:33:50.000Z", 100f)
        )

        val clickDataList = convertToMonthlyClickData(rawData)

        val entries = clickDataList.mapIndexed { index, clickData ->
            Entry(index.toFloat(), clickData.total_clicks)
        }

        val dataSet = LineDataSet(entries, "Clicks vs Months").apply {
            color = ContextCompat.getColor(this@DashBoardActivity, R.color.colorPrimary)
            valueTextColor = ContextCompat.getColor(this@DashBoardActivity, R.color.colorPrimaryDark)
            lineWidth = 2f
            setDrawCircles(true)
            setDrawValues(true)
        }

        val lineData = LineData(dataSet)

        lineChart.data = lineData

        lineChart.description.isEnabled = false
        lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(clickDataList.map { it.created_at })
        lineChart.invalidate()

        initInitializers()
        initListeners()
        initObservers()
    }

    private fun initInitializers(){
        binding.valueName.text = "TISHAN"
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val factory = MainViewModelFactory(apiService)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.getDashboardData()
    }

    private fun initObservers(){
        viewModel.dashboardData.observe(this, Observer {
            initView(it!!)
        })

        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.swipeRefreshLayout.isRefreshing = isLoading
            binding.rootLayout.isEnabled = !isLoading
        })
    }

    private fun initView(response: DashboardResponse) {
        phoneNumber = response.supportWhatsappNumber
        binding.valueGreetings.text = getGreetingMessage()
        binding.clickValue.text = response.todayClicks.toString()
        binding.locationValue.text = response.topLocation
        binding.sourceValue.text = response.topSource
        binding.verticalRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.verticalRecyclerView.adapter = if (linktype == LINK_TYPE_TOP) DashboardAdapter(response.data.topLinks) else DashboardAdapter(response.data.recentLinks)

        val rawData = mutableListOf<ClickData>()
        if (linktype == LINK_TYPE_TOP) {
            for (link in response.data.topLinks) {
                rawData.add(ClickData(link.createdAt, link.totalClicks.toFloat()))
            }
        } else {
            for (link in response.data.recentLinks) {
                rawData.add(ClickData(link.createdAt, link.totalClicks.toFloat()))
            }
        }

        val clickDataList = convertToMonthlyClickData(rawData)

        val entries = clickDataList.mapIndexed { index, clickData ->
            Entry(index.toFloat(), clickData.total_clicks)
        }

        val dataSet = LineDataSet(entries, "Clicks vs Months").apply {
            color = ContextCompat.getColor(this@DashBoardActivity, R.color.colorPrimaryBlue)
            valueTextColor = ContextCompat.getColor(this@DashBoardActivity, R.color.colorPrimaryBlue)
            lineWidth = 2f
            setDrawCircles(true)
            setDrawValues(true)
            setDrawFilled(true)
            fillDrawable = ContextCompat.getDrawable(this@DashBoardActivity, R.drawable.fade_blue)
        }

        val lineData = LineData(dataSet)

        val lineChart = findViewById<LineChart>(R.id.chart)
        lineChart.data = lineData

        lineChart.description.isEnabled = false
        lineChart.xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(clickDataList.map { it.created_at })
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f
            setLabelCount(clickDataList.size, true)
        }
        lineChart.axisLeft.apply {
            setDrawGridLines(false)
            setDrawAxisLine(false)
        }
        lineChart.axisRight.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.setDrawGridBackground(false)
        lineChart.setDrawBorders(false)
        lineChart.invalidate()
    }

    private fun initListeners(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getDashboardData()
        }

        binding.optionGroup.setOnCheckedChangeListener { _, id ->
            viewModel.getDashboardData()
            when(id){
                R.id.top_link_radio -> {linktype = LINK_TYPE_TOP}
                R.id.recent_link_radio -> {linktype = LINK_TYPE_RECENT}
            }
        }

        binding.talkWithUsBtn.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=$phoneNumber"
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                intent.setPackage("com.whatsapp")
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "WhatsApp not installed.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.faqBtn.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
            }
            try {
                startActivity(emailIntent)
            } catch (e: Exception) {
                Toast.makeText(this, "No email application found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getGreetingMessage(): String {
        val currentHour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            java.time.LocalTime.now().hour
        } else {
            return "Welcome"
        }
        return when (currentHour) {
            in 0..11 -> "Good Morning"
            in 12..17 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }

    companion object{
        const val LINK_TYPE_TOP = "LINK_TYPE_TOP"
        const val LINK_TYPE_RECENT = "LINK_TYPE_RECENT"
    }
}