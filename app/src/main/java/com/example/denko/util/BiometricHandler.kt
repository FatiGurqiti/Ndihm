package com.example.denko.util

import androidx.biometric.*
import androidx.core.content.ContextCompat
import com.example.denko.domain.model.Biometric

class BiometricHandler {
    private lateinit var biometricDetails: Biometric

    fun setup(biometricDetails: Biometric) {
        this.biometricDetails = biometricDetails
    }

    fun isBiometricAvailable(): Boolean {
        val biometricManager = BiometricManager.from(biometricDetails.activity)

        val isBiometricAvailable =
            biometricManager.canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_STRONG
                        or BiometricManager.Authenticators.DEVICE_CREDENTIAL
            )

        return isBiometricAvailable == BiometricManager.BIOMETRIC_SUCCESS
    }

    fun launch(onSuccess: (result: BiometricPrompt.AuthenticationResult) -> Unit, close: Boolean) {
        if (!isBiometricAvailable()) return

        val executor = ContextCompat.getMainExecutor(biometricDetails.activity)
        val biometricPrompt = BiometricPrompt(
            biometricDetails.activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess(result)
                }

            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .setTitle(biometricDetails.title)
            .setSubtitle(biometricDetails.subtext)
            .setNegativeButtonText(biometricDetails.negativeText)
            .build()

        if (!close) biometricPrompt.authenticate(promptInfo)
        else biometricPrompt.cancelAuthentication()
    }
}