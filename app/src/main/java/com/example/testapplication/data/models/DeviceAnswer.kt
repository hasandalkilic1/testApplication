package com.example.testapplication.data.models

import com.google.gson.annotations.SerializedName

data class DeviceAnswer(
    @SerializedName("Devices") var Devices: List<Device>
) {}