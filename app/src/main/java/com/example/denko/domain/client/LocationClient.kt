package com.example.denko.domain.client

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdated(interval: Long): Flow<Location>

    class LocationException(message: String) : Exception()
}