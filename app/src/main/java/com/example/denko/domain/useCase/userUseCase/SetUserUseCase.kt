package com.example.denko.domain.useCase.userUseCase

import com.example.denko.domain.model.User
import com.example.denko.domain.repository.UserRepository

class SetUserUseCase(private val userRepository: UserRepository) {
    operator fun invoke(user: User) {
        userRepository.setUser(user)
    }
}