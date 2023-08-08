package com.example.testapplication.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.testapplication.data.models.Device
import com.example.testapplication.data.source.local.DeviceDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDeviceRepository(var deviceDao: DeviceDao) : IDeviceRepository {

    override fun getAllDevices(livedata: MutableLiveData<List<Device>>) {
        CoroutineScope(Dispatchers.IO).launch {
            livedata.postValue(deviceDao.allDevices())
        }
    }

    fun deleteByDevicePK(PK_Device: Int?) {
        deviceDao.deleteByDevicePK(PK_Device)
    }
}