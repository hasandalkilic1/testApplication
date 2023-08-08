package com.example.testapplication.di

import android.content.Context
import androidx.room.Room
import com.example.testapplication.data.repo.LocalDeviceRepository
import com.example.testapplication.data.repo.RemoteDeviceRepository
import com.example.testapplication.data.source.local.DeviceDao
import com.example.testapplication.data.source.local.DeviceDatabase
import com.example.testapplication.data.source.remote.ApiUtils
import com.example.testapplication.data.source.remote.DeviceRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideLocalDeviceRepository(
        deviceDao: DeviceDao
    ): LocalDeviceRepository {
        return LocalDeviceRepository(deviceDao)
    }

    @Provides
    @Singleton
    fun provideRemoteDeviceRepository(
        deviceRemote: DeviceRemote,
        deviceDao: DeviceDao,
        @ApplicationContext context: Context
    ): RemoteDeviceRepository {
        return RemoteDeviceRepository(deviceRemote, deviceDao, context)
    }

    @Provides
    @Singleton
    fun provideDeviceRemote(): DeviceRemote {
        return ApiUtils.getDevices()
    }

    @Provides
    @Singleton
    fun provideDeviceDao(@ApplicationContext context: Context): DeviceDao {
        val db = Room.databaseBuilder(context, DeviceDatabase::class.java, "Devices.sqlite")
            .createFromAsset("Devices.sqlite").build()
        return db.getDeviceDao()
    }
}