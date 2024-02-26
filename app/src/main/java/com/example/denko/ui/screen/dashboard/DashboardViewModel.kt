package com.example.denko.ui.screen.dashboard

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import android.provider.Settings
import com.example.denko.common.hasLocationPermission
import com.example.denko.data.remote.firebase.RealtimeDataBaseHandler
import com.example.denko.domain.model.User
import com.example.denko.domain.useCase.helpActionUseCase.HelpActionUseCases
import com.example.denko.service.location.LocationService
import com.example.denko.ui.screen.base.BaseViewModel
import com.example.denko.util.BiometricHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val biometricHandler: BiometricHandler,
    private val helpActionUseCases: HelpActionUseCases
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
        setState { copy(helpActionActive = helpActionUseCases.getHelpActionUseCase()) }
    }

    private fun Context.helpButtonClick() {
        if (!state.value.helpActionActive) startHelpAction()
        else endHelpAction()
    }

    private fun Context.startHelpAction() {
        if (biometricHandler.isBiometricAvailable()) launchBiometrics()
        else setState { copy(confirmDialogVisibility = true) }
    }

    private fun Context.endHelpAction() {
        setState { copy(helpActionActive = false) }
        helpActionUseCases.setHelpActionUseCase(false)
        Intent(this, LocationService::class.java).apply {
            action = LocationService.ACTION_STOP
            startService(this)
        }
    }

    private fun Context.launchBiometrics() {
        biometricHandler.launch(
            onSuccess = { helpAction() },
            close = false
        )
    }

    private fun Context.helpAction() {
        if (!checkInternetAndLocationStatus()) return

        setState {
            copy(
                confirmDialogVisibility = false,
                helpActionActive = true,
            )
        }
//        realtimeDataBaseHandler.writeNewUser(User("Fati", "Gurqiti", "044", listOf("123.23123")))
        val realtimeDataBaseHandler = RealtimeDataBaseHandler()
        realtimeDataBaseHandler.addNewValue()
        return
        helpActionUseCases.setHelpActionUseCase(true)
        Intent(this, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }

    private fun Context.checkInternetAndLocationStatus(): Boolean {
        val locationManager =
            applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val networkInfo = connectivityManager!!.activeNetworkInfo
        val isNetworkEnabled = networkInfo != null && networkInfo.isConnected

        if (!isGpsEnabled) {
            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
                startActivity(this)
            }
        } else if (!isNetworkEnabled) {
            Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS).apply {
                startActivity(this)
            }
        }
        return hasLocationPermission() && isGpsEnabled && isNetworkEnabled
    }
}