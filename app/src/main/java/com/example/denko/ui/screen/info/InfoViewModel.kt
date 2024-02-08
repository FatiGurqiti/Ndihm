package com.example.denko.ui.screen.info

import com.example.denko.domain.useCase.userUseCase.SetUserUseCase
import com.example.denko.ui.screen.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val setUserUseCase: SetUserUseCase,
) : BaseViewModel<InfoState, InfoEvent, InfoEffect>() {
    override val initialState: InfoState
        get() = InfoState

    override fun onEvent(event: InfoEvent) {
        when (event) {
            is InfoEvent.SetupUser -> {
                setUserUseCase(event.user)
                InfoEffect.RedirectToDashboard.setEffect()
            }
        }
    }
}