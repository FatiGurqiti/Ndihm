package com.example.denko.ui.screen.dashboard

import com.example.denko.ui.screen.base.BaseViewModel
import com.example.denko.util.BiometricHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val biometricHandler: BiometricHandler
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
        println("help started")
        setState { copy(confirmDialogVisibility = false)}
    }
}