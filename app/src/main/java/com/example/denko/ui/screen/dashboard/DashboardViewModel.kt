package com.example.denko.ui.screen.dashboard

import com.example.denko.domain.useCase.helpActionUseCase.HelpActionUseCases
import com.example.denko.ui.screen.base.BaseViewModel
import com.example.denko.util.BiometricHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val biometricHandler: BiometricHandler,
    private val helpActionUseCases: HelpActionUseCases
) :
    BaseViewModel<DashboardState, DashboardEvent, DashboardEffect>() {
    override val initialState: DashboardState
        get() = DashboardState()

    override fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.OnHelpButtonClick -> helpButtonClick()
            DashboardEvent.CloseConfirmDialog -> setState { copy(confirmDialogVisibility = false) }
            DashboardEvent.HelpAction -> helpAction()
        }
    }

    init {
        setupInitialStates()
    }

    private fun setupInitialStates() {
        setState { copy(helpActionActive = helpActionUseCases.getHelpActionUseCase()) }
    }

    private fun helpButtonClick() {
        if (biometricHandler.isBiometricAvailable()) launchBiometrics()
        else setState { copy(confirmDialogVisibility = true) }
    }

    private fun launchBiometrics() {
        biometricHandler.launch(
            onSuccess = { helpAction() },
            close = false
        )
    }

    private fun helpAction() {
        setState { copy(confirmDialogVisibility = false, helpActionActive = true) }
        helpActionUseCases.setHelpActionUseCase(true)
    }
}