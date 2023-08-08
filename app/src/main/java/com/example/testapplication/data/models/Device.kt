package com.example.testapplication.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Devices")
@Parcelize
data class Device(
    @ColumnInfo(name = "PK_Device") @PrimaryKey var PK_Device: Int,
    @ColumnInfo(name = "MacAddress") var MacAddress: String,
    @ColumnInfo(name = "PK_DeviceType") var PK_DeviceType: Int,
    @ColumnInfo(name = "PK_DeviceSubType") var PK_DeviceSubType: Int,
    @ColumnInfo(name = "Firmware") var Firmware: String,
    @ColumnInfo(name = "Server_Device") var Server_Device: String,
    @ColumnInfo(name = "Server_Event") var Server_Event: String,
    @ColumnInfo(name = "Server_Account") var Server_Account: String,
    @ColumnInfo(name = "InternalIP") var InternalIP: String,
    @ColumnInfo(name = "LastAliveReported") var LastAliveReported: String,
    @ColumnInfo(name = "Platform") var Platform: String,
    @ColumnInfo(name = "PK_Account") var PK_Account: Int
) : Parcelable {
    private companion object : Parceler<Device> {
        override fun create(parcel: Parcel): Device {
            return Device(
                parcel.readInt(),
                parcel.readString()!!,
                parcel.readInt(),
                parcel.readInt()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readInt()
            )
        }

        override fun Device.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(PK_Device)
            parcel.writeString(MacAddress)
            parcel.writeInt(PK_DeviceType)
            parcel.writeInt(PK_DeviceSubType)
            parcel.writeString(Firmware)
            parcel.writeString(Server_Device)
            parcel.writeString(Server_Event)
            parcel.writeString(Server_Account)
            parcel.writeString(InternalIP)
            parcel.writeString(LastAliveReported)
            parcel.writeString(Platform)
            parcel.writeInt(PK_Account)
        }

    }
}
