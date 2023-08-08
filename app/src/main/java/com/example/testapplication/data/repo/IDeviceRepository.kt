package com.example.testapplication.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.testapplication.data.models.Device

interface IDeviceRepository {
    fun getAllDevices(livedata: MutableLiveData<List<Device>>)
}