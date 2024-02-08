package com.example.denko.di

import android.content.SharedPreferences
import com.example.denko.data.local.preferences.NdihdenPreferences
import com.example.denko.util.BiometricHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesBiometricHandler(): BiometricHandler = BiometricHandler()

    @Singleton
    @Provides
    fun providesNdihdenPreferences(sharedPreferences: SharedPreferences): NdihdenPreferences =
        NdihdenPreferences(sharedPreferences)
}