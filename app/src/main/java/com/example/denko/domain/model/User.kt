package com.example.denko.domain.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var id: String? = null,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    var location: ArrayList<Location> = arrayListOf()
)
