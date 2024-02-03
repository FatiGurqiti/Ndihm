package com.example.denko.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.example.denko.domain.model.Biometric
import com.example.denko.ui.screen.main.MainScreen
import com.example.denko.ui.theme.DenkoTheme
import com.example.denko.util.BiometricHandler

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        setContent()
    }

    private fun setup() {
        initBiometric()
    }

    private fun initBiometric() {
        val biometricHandler = BiometricHandler()
        val biometric = Biometric(
            activity = this,
            title = "title",
            subtext = "subtext",
            negativeText = "negativeText"
        )

        biometricHandler.setup(biometric)
    }

    private fun setContent() {
        setContent {
            DenkoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
