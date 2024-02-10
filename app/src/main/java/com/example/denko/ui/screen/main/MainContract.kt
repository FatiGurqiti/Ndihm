package com.example.denko.ui.screen.main

import android.content.Context
import com.example.denko.domain.model.Biometric
import com.example.denko.domain.model.Permission
import com.example.denko.ui.screen.base.ViewEffect
import com.example.denko.ui.screen.base.ViewEvent
import com.example.denko.ui.screen.base.ViewState

data class MainState(
    val isUserSet: Boolean = false,
    val permissionsEnabled: Boolean = false
) : ViewState()

sealed class MainEvent : ViewEvent() {
    data class SetupBiometrics(val biometric: Biometric) : MainEvent()
    data class CheckPermissionEnabled(val permission: Permission) : MainEvent()
    data class RedirectToPermissions(val context: Context) : MainEvent()
    data class PermissionsStatusChanged(val enabled: Boolean) : MainEvent()
}

sealed class MainEffect : ViewEffect()