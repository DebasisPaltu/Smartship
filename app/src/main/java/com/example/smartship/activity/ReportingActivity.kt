package com.example.smartship.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.smartship.R
import com.example.smartship.repository.ReadingRepository
import com.example.smartship.services.AppDatabase
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReportingActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporting)

        val readingId = intent.getIntExtra("readingId", -1)
        val lineChart: LineChart = findViewById(R.id.lineChart)

        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
        val repository = ReadingRepository(database.readingDao())

        lifecycleScope.launch {
            val readings = withContext(Dispatchers.IO) {
                repository.getAllReadings()
            }

            val entries = readings.map { Entry(it.timestamp.toFloat(), it.value) }
            val dataSet = LineDataSet(entries, "Temperature")
            val lineData = LineData(dataSet)

            lineChart.data = lineData
            lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            lineChart.invalidate()
        }
    }
}
