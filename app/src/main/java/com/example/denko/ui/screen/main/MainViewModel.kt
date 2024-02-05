package com.example.denko.ui.screen.main

import com.example.denko.common.Constants.USER_STATE
import com.example.denko.data.local.NdihdenPreferences
import com.example.denko.ui.screen.base.BaseViewModel
import com.example.denko.util.BiometricHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val biometricHandler: BiometricHandler,
    private val ndihdenPreferences: NdihdenPreferences,
) : BaseViewModel<MainState, MainEvent, MainEffect>() {

    override val initialState: MainState
        get() = MainState()

    override fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.SetupBiometrics -> {
                biometricHandler.setup(event.biometric)
            }
        }
    }

    init {
        setupUserState()
    }

    private fun setupUserState() {
        setState { copy(isUserSet = ndihdenPreferences.getBoolean(USER_STATE, false)) }
    }
}