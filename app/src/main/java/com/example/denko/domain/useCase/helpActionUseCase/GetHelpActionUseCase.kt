package com.example.denko.domain.useCase.helpActionUseCase

import com.example.denko.domain.repository.HelpActionRepository

class GetHelpActionUseCase(private val helpActionRepository: HelpActionRepository) {
    operator fun invoke() = helpActionRepository.getHelpAction()
}