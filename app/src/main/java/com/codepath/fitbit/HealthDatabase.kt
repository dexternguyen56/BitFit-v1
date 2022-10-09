package com.codepath.fitbit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HealthEntity::class], version = 1)
abstract class HealthDatabase : RoomDatabase() {

    abstract fun HealthDAO(): HealthDAO

    companion object {

        @Volatile
        private var INSTANCE: HealthDatabase? = null

        fun getInstance(context: Context): HealthDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HealthDatabase::class.java, "Health-db"
            ).build()
    }
}