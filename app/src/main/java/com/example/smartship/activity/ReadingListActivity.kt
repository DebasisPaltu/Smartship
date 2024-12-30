package com.example.smartship.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.smartship.R
import com.example.smartship.adapter.ReadingAdapter
import com.example.smartship.repository.ReadingRepository
import com.example.smartship.services.AppDatabase
import kotlinx.coroutines.launch

class ReadingListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReadingAdapter
    private lateinit var repository: ReadingRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reading_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
        repository = ReadingRepository(database.readingDao())

        lifecycleScope.launch {
            val readings = repository.getAllReadings()
            if (readings.isEmpty()) {
                Log.d("ReadingListActivity", "No readings found in the database.")
            } else {
                Log.d("ReadingListActivity", "Readings found: ${readings.size}")
            }
            adapter = ReadingAdapter(readings)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged() // Notify adapter about data changes
        }
    }
}


