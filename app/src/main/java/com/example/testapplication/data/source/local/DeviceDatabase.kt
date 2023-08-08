package com.example.testapplication.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapplication.data.models.Device

@Database(entities = [Device::class], version = 1)
abstract class DeviceDatabase : RoomDatabase() {
    abstract fun getDeviceDao(): DeviceDao
}