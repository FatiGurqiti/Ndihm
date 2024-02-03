package com.example.denko.domain.model

import androidx.fragment.app.FragmentActivity

data class Biometric(
    val activity: FragmentActivity,
    val title: String,
    val subtext: String,
    val negativeText: String
)