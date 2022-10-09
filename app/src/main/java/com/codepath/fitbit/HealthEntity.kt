package com.codepath.fitbit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_table")
data class HealthEntity (

    @ColumnInfo(name = "calories") val calories: String?,
    @ColumnInfo(name = "food") val title: String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0

)