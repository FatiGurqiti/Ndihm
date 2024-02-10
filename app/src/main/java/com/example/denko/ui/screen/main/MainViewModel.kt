package com.example.denko.ui.screen.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.example.denko.domain.useCase.userUseCase.GetUserUseCase
import com.example.denko.ui.screen.base.BaseViewModel
import com.example.denko.util.BiometricHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val biometricHandler: BiometricHandler,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel<MainState, MainEvent, MainEffect>() {

    override val initialState: MainState
        get() = MainState()

    override fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.SetupBiometrics -> {
                biometricHandler.setup(event.biometric)
            }

            is MainEvent.CheckPermissionEnabled -> {
                val permissions = arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )

                event.permission.apply {
                    val permissionsEnabled = permissions.all {
                        ContextCompat.checkSelfPermission(
                            context,
                            it
                        ) == PackageManager.PERMISSION_GRANTED
                    }

                    if (!permissionsEnabled) launcher.launch(permissions)
                    else setState { copy(permissionsEnabled = true) }
                }
            }

            is MainEvent.RedirectToPermissions -> {
                event.context.apply {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.fromParts("package", packageName, null)
                    (this as Activity).startActivityForResult(intent, 0)
                }
            }

            is MainEvent.PermissionsStatusChanged -> {
                setState { copy(permissionsEnabled = event.enabled) }
            }
        }
    }

    init {
        setupUserState()
    }

    private fun setupUserState() {
        setState { copy(isUserSet = getUserUseCase() != null) }
    }
}