package com.example.denko.di

import android.content.Context
import android.content.SharedPreferences
import com.example.denko.common.Constants.SHARED_PREFERENCES
import com.example.denko.data.local.NdihdenPreferences
import com.example.denko.util.BiometricHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesNdihdenPreferences(sharedPreferences: SharedPreferences): NdihdenPreferences =
        NdihdenPreferences(sharedPreferences)
}