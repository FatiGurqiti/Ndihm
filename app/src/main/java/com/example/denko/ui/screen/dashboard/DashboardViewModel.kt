package com.example.denko.ui.screen.dashboard

import android.content.Context
import android.content.Intent
import com.example.denko.domain.useCase.helpActionUseCase.HelpActionUseCases
import com.example.denko.service.location.LocationService
import com.example.denko.ui.screen.base.BaseViewModel
import com.example.denko.util.BiometricHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val biometricHandler: BiometricHandler,
    private val helpActionUseCases: HelpActionUseCases,
) :
    BaseViewModel<DashboardState, DashboardEvent, DashboardEffect>() {
    override val initialState: DashboardState
        get() = DashboardState()

    override fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.OnHelpButtonClick -> event.context.helpButtonClick()
            DashboardEvent.CloseConfirmDialog -> setState { copy(confirmDialogVisibility = false) }
            is DashboardEvent.HelpAction -> event.context.helpAction()
        }
    }

    init {
        setupInitialStates()
    }

    private fun setupInitialStates() {
//        setState { copy(helpActionActive = helpActionUseCases.getHelpActionUseCase()) }
        setState { copy(helpActionActive = false) }
    }

    private fun Context.helpButtonClick() {
        if (biometricHandler.isBiometricAvailable()) launchBiometrics()
        else setState { copy(confirmDialogVisibility = true) }
    }

    private fun Context.launchBiometrics() {
        biometricHandler.launch(
            onSuccess = { helpAction() },
            close = false
        )
    }

    private fun Context.helpAction() {
//        setState { copy(confirmDialogVisibility = false, helpActionActive = false) }
//        helpActionUseCases.setHelpActionUseCase(true)
        Intent(this, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }
}