package com.example.denko.di

import com.example.denko.domain.repository.UserRepository
import com.example.denko.domain.useCase.userUseCase.GetUserUseCase
import com.example.denko.domain.useCase.userUseCase.SetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun providesGetUserUseCase(userRepository: UserRepository) = GetUserUseCase(userRepository)

    @Singleton
    @Provides
    fun providesSetUserUseCase(userRepository: UserRepository) = SetUserUseCase(userRepository)
}