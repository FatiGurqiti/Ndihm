package com.example.denko.ui.screen.main

import com.example.denko.domain.model.Biometric
import com.example.denko.ui.screen.base.ViewEffect
import com.example.denko.ui.screen.base.ViewEvent
import com.example.denko.ui.screen.base.ViewState

data class MainState(
    val isUserSet: Boolean = false
) : ViewState()

sealed class MainEvent : ViewEvent() {
    data class SetupBiometrics(val biometric: Biometric) : MainEvent()
}

sealed class MainEffect : ViewEffect()