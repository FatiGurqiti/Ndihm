package com.example.denko.domain.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val name: String,
    val surname: String,
    val number: String,
    val location: List<String> = emptyList()
)
