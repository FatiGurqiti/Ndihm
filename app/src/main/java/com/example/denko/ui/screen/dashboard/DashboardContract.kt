package com.example.denko.ui.screen.dashboard

import com.example.denko.ui.screen.base.ViewEffect
import com.example.denko.ui.screen.base.ViewEvent
import com.example.denko.ui.screen.base.ViewState

data class DashboardState(
    val confirmDialogVisibility: Boolean = false,
    val helpActionActive: Boolean = false
) : ViewState()

sealed class DashboardEvent : ViewEvent() {
    data object OnHelpButtonClick : DashboardEvent()
    data object CloseConfirmDialog : DashboardEvent()
    data object HelpAction : DashboardEvent()
}

sealed class DashboardEffect : ViewEffect()