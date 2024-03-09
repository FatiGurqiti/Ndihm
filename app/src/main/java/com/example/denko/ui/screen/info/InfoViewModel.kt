package com.example.denko.ui.screen.info

import com.example.denko.domain.useCase.userUseCase.SetUserUseCase
import com.example.denko.ui.screen.base.BaseViewModel
import com.google.firebase.installations.FirebaseInstallations
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val setUserUseCase: SetUserUseCase,
) : BaseViewModel<InfoState, InfoEvent, InfoEffect>() {
    private val firebaseInstallations = FirebaseInstallations.getInstance()

    override val initialState: InfoState
        get() = InfoState()

    override fun onEvent(event: InfoEvent) {
        when (event) {
            is InfoEvent.SetupUser -> {
                if (event.user.phoneNumber.isEmpty() || event.user.phoneNumber.length < 8) {
                    setState { copy(phoneFieldError = true) }
                } else {
                    firebaseInstallations.id.addOnSuccessListener {
                        event.user.id = it
                        setUserUseCase(event.user)
                    }.addOnFailureListener {
                        event.user.id = generateRandomString()
                        setUserUseCase(event.user)
                    }
                    InfoEffect.RedirectToDashboard.setEffect()
                }
            }
        }
    }


    private fun generateRandomString(): String {
        val uuidString = UUID.randomUUID().toString().replace("-", "")  // Remove hyphens
        return uuidString.substring(0, 16)
    }
}