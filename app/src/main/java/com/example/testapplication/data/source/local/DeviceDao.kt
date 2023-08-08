package com.example.testapplication.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testapplication.data.models.Device

@Dao
interface DeviceDao {
    @Query("SELECT * FROM Devices")
    suspend fun allDevices(): List<Device>

    @Insert
    suspend fun addDevice(device: Device)

    @Query("DELETE FROM Devices")
    suspend fun clearAllTable()

    @Query("DELETE FROM Devices WHERE PK_Device = :PK_Device")
    fun deleteByDevicePK(PK_Device: Int?)
}