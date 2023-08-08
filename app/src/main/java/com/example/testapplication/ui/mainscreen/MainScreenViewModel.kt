package com.example.testapplication.ui.mainscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapplication.data.models.Device
import com.example.testapplication.data.repo.LocalDeviceRepository
import com.example.testapplication.data.repo.RemoteDeviceRepository
import com.example.testapplication.utils.SharedPreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private var remoteDeviceRepository: RemoteDeviceRepository,
    var localDeviceRepository: LocalDeviceRepository
) : ViewModel() {
    var deviceList = MutableLiveData<List<Device>>()

    init {
        getAllDevices()
    }

    fun refreshList() {
        remoteDeviceRepository.getAllDevices(deviceList)
    }

    fun deleteByDevicePK(PK_Device: Int?) {
        val executorService = Executors.newSingleThreadExecutor()
        executorService.execute { localDeviceRepository.deleteByDevicePK(PK_Device) }
    }

    fun getAllDevices() {
        val currentTime = Calendar.getInstance().timeInMillis

        val dateOfGetService =
            SharedPreUtil().getString("dateOfGetService", "", remoteDeviceRepository.context)

        if (dateOfGetService.isEmpty()) {
            remoteDeviceRepository.getAllDevices(deviceList)
        } else if ((currentTime - dateOfGetService.toLong()) < 5 * 60 * 1000) {
            localDeviceRepository.getAllDevices(deviceList)
        } else if ((currentTime - dateOfGetService.toLong()) >= 5 * 60 * 1000) {
            remoteDeviceRepository.getAllDevices(deviceList)
        }
        if (deviceList.value.isNullOrEmpty()) {
            deviceList.value = remoteDeviceRepository.getMockData()
        }
    }
}