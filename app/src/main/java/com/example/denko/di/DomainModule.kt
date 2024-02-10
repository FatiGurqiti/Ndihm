package com.example.denko.di

import com.example.denko.domain.repository.HelpActionRepository
import com.example.denko.domain.repository.UserRepository
import com.example.denko.domain.useCase.helpActionUseCase.GetHelpActionUseCase
import com.example.denko.domain.useCase.helpActionUseCase.HelpActionUseCases
import com.example.denko.domain.useCase.helpActionUseCase.SetHelpActionUseCase
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

    @Singleton
    @Provides
    fun providesGetHelpActionUseCase(helpActionRepository: HelpActionRepository) =
        GetHelpActionUseCase(helpActionRepository)

    @Singleton
    @Provides
    fun providesSetHelpActionUseCase(helpActionRepository: HelpActionRepository) =
        SetHelpActionUseCase(helpActionRepository)

    @Singleton
    @Provides
    fun providesHelpActionUseCases(
        getHelpActionUseCase: GetHelpActionUseCase,
        setHelpActionUseCase: SetHelpActionUseCase
    ) = HelpActionUseCases(getHelpActionUseCase, setHelpActionUseCase)
}