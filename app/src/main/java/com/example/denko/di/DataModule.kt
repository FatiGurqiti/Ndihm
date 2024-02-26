package com.example.denko.di

import android.content.Context
import android.content.SharedPreferences
import com.example.denko.common.Constants
import com.example.denko.data.local.preferences.NdihdenPreferences
import com.example.denko.data.local.repository.HelpActionRepositoryImpl
import com.example.denko.data.local.repository.UserRepositoryImpl
import com.example.denko.data.remote.firebase.RealtimeDataBaseHandler
import com.example.denko.domain.repository.HelpActionRepository
import com.example.denko.domain.repository.UserRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providesGson() = Gson()

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesUserRepository(ndihdenPreferences: NdihdenPreferences, gson: Gson): UserRepository =
        UserRepositoryImpl(ndihdenPreferences, gson)

    @Singleton
    @Provides
    fun providesHelpActionRepository(ndihdenPreferences: NdihdenPreferences): HelpActionRepository =
        HelpActionRepositoryImpl(ndihdenPreferences)

    @Singleton
    @Provides
    fun providesRealtimeDataBaseHandler() : RealtimeDataBaseHandler = RealtimeDataBaseHandler()
}