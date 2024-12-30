package com.example.smartship.repository

import com.example.smartship.model.Reading
import com.example.smartship.services.ReadingDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReadingRepository(private val readingDao: ReadingDao) {

    suspend fun getAllReadings(): List<Reading> {
        return withContext(Dispatchers.IO) {
            readingDao.getAll()
        }
    }

    suspend fun insertReading(reading: Reading) {
        withContext(Dispatchers.IO) {
            readingDao.insert(reading)
        }
    }

    suspend fun deleteReading(reading: Reading) {
        withContext(Dispatchers.IO) {
            readingDao.delete(reading)
        }
    }
}