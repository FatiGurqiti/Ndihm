package com.example.denko.domain.useCase.helpActionUseCase

import com.example.denko.domain.repository.HelpActionRepository

class SetHelpActionUseCase(private val helpActionRepository: HelpActionRepository) {
    operator fun invoke(value: Boolean) {
        helpActionRepository.setHelpAction(value)
    }
}