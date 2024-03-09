package com.example.denko.domain.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Location(
    val longitude: String,
    val latitude: String
)