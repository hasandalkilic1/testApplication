package com.example.testapplication.data.repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.testapplication.data.models.Device
import com.example.testapplication.data.models.DeviceAnswer
import com.example.testapplication.data.source.local.DeviceDao
import com.example.testapplication.data.source.remote.DeviceRemote
import com.example.testapplication.utils.SharedPreUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import java.util.Calendar

class RemoteDeviceRepository(
    var deviceRemote: DeviceRemote,
    var deviceDao: DeviceDao,
    var context: Context
) : IDeviceRepository {
    fun addDeviceToDb(
        PK_Device: Int,
        MacAddress: String,
        PK_DeviceType: Int,
        PK_DeviceSubType: Int,
        Firmware: String,
        Server_Device: String,
        Server_Event: String,
        Server_Account: String,
        InternalIP: String,
        LastAliveReported: String,
        Platform: String,
        PK_Account: Int
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val newDevice =
                Device(PK_Device, MacAddress, PK_DeviceType, PK_DeviceSubType, Firmware, Server_Device, Server_Event, Server_Account, InternalIP, LastAliveReported, Platform, PK_Account)
            deviceDao.addDevice(newDevice)
        }
    }

    override fun getAllDevices(liveData: MutableLiveData<List<Device>>) {
        deviceRemote.allDevices().enqueue(object : Callback<DeviceAnswer> {
            override fun onResponse(
                call: Call<DeviceAnswer>?,
                response: Response<DeviceAnswer>
            ) {
                val list = response.body().Devices
                liveData.postValue(list)

                CoroutineScope(Dispatchers.IO).launch {
                    deviceDao.clearAllTable()
                }

                for (i in list) {
                    addDeviceToDb(
                        i.PK_Device,
                        i.MacAddress,
                        i.PK_DeviceType,
                        i.PK_DeviceSubType,
                        i.Firmware,
                        i.Server_Device,
                        i.Server_Event,
                        i.Server_Account,
                        i.InternalIP,
                        i.LastAliveReported,
                        i.Platform,
                        i.PK_Account
                    )
                }

                val currentTime = Calendar.getInstance().timeInMillis

                val sharedPref = SharedPreUtil()
                sharedPref.putString("dateOfGetService", currentTime.toString(), context)
                liveData.postValue(list)
            }

            override fun onFailure(call: Call<DeviceAnswer>?, t: Throwable?) {
                CoroutineScope(Dispatchers.IO).launch {
                    liveData.postValue(deviceDao.allDevices())
                }
            }
        })
    }

    fun getMockData(): List<Device> {
        val list = ArrayList<Device>()
        list.add(
            Device(
                0,
                "test",
                0,
                0,
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                "test",
                0
            )
        )

        for (i in list) {
            addDeviceToDb(
                i.PK_Device,
                i.MacAddress,
                i.PK_DeviceType,
                i.PK_DeviceSubType,
                i.Firmware,
                i.Server_Device,
                i.Server_Event,
                i.Server_Account,
                i.InternalIP,
                i.LastAliveReported,
                i.Platform,
                i.PK_Account
            )
        }
        return list
    }
}
