package com.example.testapplication.data.source.remote

class ApiUtils {
    companion object {
        val BASE_URL = "https://veramobile.mios.com/test_android/items.test/"

        fun getDevices(): DeviceRemote {
            return RetrofitClient.getClient(BASE_URL).create(DeviceRemote::class.java)
        }
    }
}