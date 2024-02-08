package com.example.denko.domain.useCase.userUseCase

import com.example.denko.domain.repository.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    operator fun invoke() = userRepository.getUser()
}