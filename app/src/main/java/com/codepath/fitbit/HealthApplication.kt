package com.codepath.fitbit

import android.app.Application

class HealthApplication : Application() {
    val db by lazy { HealthDatabase.getInstance(this) }
}