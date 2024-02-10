package com.example.denko.ui.screen.info

import com.example.denko.domain.model.User
import com.example.denko.ui.screen.base.ViewEffect
import com.example.denko.ui.screen.base.ViewEvent
import com.example.denko.ui.screen.base.ViewState

data class InfoState(
    val phoneFieldError: Boolean = false
) : ViewState()

sealed class InfoEvent : ViewEvent() {
    data class SetupUser(val user: User) : InfoEvent()
}

sealed class InfoEffect : ViewEffect() {
    data object RedirectToDashboard : InfoEffect()
}