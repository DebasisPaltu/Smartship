package com.example.smartship.services

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.smartship.model.Reading

@Dao
interface ReadingDao {
    @Query("SELECT * FROM reading")
    fun getAll(): List<Reading>

    @Insert
    suspend fun insert(vararg readings: Reading)

    @Delete
    suspend fun delete(reading: Reading)
}

@Database(entities = [Reading::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun readingDao(): ReadingDao
}