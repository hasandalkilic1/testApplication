package com.example.testapplication.data.source.remote

import com.example.testapplication.data.models.DeviceAnswer
import retrofit2.Call
import retrofit2.http.GET

interface DeviceRemote {
    @GET("Devices")
    fun allDevices(): Call<DeviceAnswer>
}