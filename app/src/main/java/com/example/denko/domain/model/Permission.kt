package com.example.denko.domain.model

import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher

data class Permission(
    val context: Context,
    val launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>
)