package com.codepath.fitbit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthDAO {
    @Query("SELECT * FROM health_table")
    fun getAll(): Flow<MutableList<HealthEntity>>

    @Insert
    fun insertAll(HealthItem: MutableList<HealthEntity>)

    @Insert
    fun insert(HealthItem: HealthEntity)

    @Query("DELETE FROM health_table")
    fun deleteAll()

}