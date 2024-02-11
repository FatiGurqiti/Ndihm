package com.example.denko.ui.screen.dashboard

import android.content.Context
import com.example.denko.ui.screen.base.ViewEffect
import com.example.denko.ui.screen.base.ViewEvent
import com.example.denko.ui.screen.base.ViewState

data class DashboardState(
    val confirmDialogVisibility: Boolean = false,
    val helpActionActive: Boolean = false
) : ViewState()

sealed class DashboardEvent : ViewEvent() {
    data class OnHelpButtonClick(val context: Context) : DashboardEvent()
    data object CloseConfirmDialog : DashboardEvent()
    data class HelpAction(val context: Context) : DashboardEvent()
}

sealed class DashboardEffect : ViewEffect()