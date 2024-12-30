package com.example.smartship.model

import androidx.room.*

@Entity
data class Reading(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
    val value: Float,
    val isOffline: Boolean
)





