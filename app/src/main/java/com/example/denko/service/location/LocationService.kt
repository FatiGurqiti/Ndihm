package com.example.denko.service.location

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.denko.R
import com.example.denko.data.remote.firebase.RealtimeDataBaseHandler
import com.example.denko.domain.client.LocationClient
import com.example.denko.domain.useCase.userUseCase.GetUserUseCase
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service() {
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient //TODO("Inject this")

    private val userLocations = arrayListOf<com.example.denko.domain.model.Location>()

    @Inject
    lateinit var getUserUseCase: GetUserUseCase

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.location_notification_title))
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_launcher_background) //TODO("Change with logo")
            .setOngoing(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient.getLocationUpdated(LOCATION_UPDATE_ACCURACY)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                println("location: ${location.latitude}, ${location.longitude}")
                val updatedNotification =
                    notification.setContentText("${location.latitude}, ${location.longitude}")

                updateData(location)

                notificationManager.notify(1, updatedNotification.build())
            }
            .launchIn(serviceScope)

        startForeground(1, notification.build())
    }

    private fun updateData(location: Location) {
        val realtimeDataBaseHandler = RealtimeDataBaseHandler()
        getUserUseCase()?.let {

            userLocations.add(
                com.example.denko.domain.model.Location(
                    location.longitude.toString(),
                    location.latitude.toString()
                )
            )

            it.location = userLocations
            realtimeDataBaseHandler.addNewValue(it)
        }
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        const val LOCATION_UPDATE_ACCURACY = 10000L
        const val ACTION_START = "actionStart"
        const val ACTION_STOP = "actionStop"
        const val CHANNEL_ID = "location"
        const val CHANNEL_NAME = "Location"
    }
}