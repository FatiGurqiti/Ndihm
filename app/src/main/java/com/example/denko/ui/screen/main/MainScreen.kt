package com.example.denko.ui.screen.main

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.denko.R
import com.example.denko.domain.model.Biometric
import com.example.denko.domain.model.Permission
import com.example.denko.ui.navigation.Navigation
import com.example.denko.ui.screen.main.composable.PermissionScreen
import com.example.denko.ui.theme.DenkoTheme
import kotlin.reflect.KFunction1

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current
    val navController = rememberNavController()
    val context = LocalContext.current
    val state: MainState by viewModel.state.collectAsStateWithLifecycle()
    val setEvent = viewModel::setEvent

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        setEvent(MainEvent.PermissionsStatusChanged(areGranted))
    }

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    context.apply {
                        val biometric = Biometric(
                            activity = this as FragmentActivity,
                            title = getString(R.string.biometric_title),
                            subtext = getString(R.string.biometric_subtext),
                            negativeText = getString(R.string.cancel)
                        )

                        setEvent(MainEvent.SetupBiometrics(biometric))
                    }
                }

                Lifecycle.Event.ON_RESUME -> {
                    val permission = Permission(context, launcherMultiplePermissions)
                    setEvent(MainEvent.CheckPermissionEnabled(permission))
                }

                else -> Unit
            }
        }

        lifecycle.lifecycle.addObserver(observer)

        onDispose {
            lifecycle.lifecycle.removeObserver(observer)
        }
    }

    MainContent(
        navController = navController,
        state = state,
        setEvent = setEvent,
        context = context
    )
}

@Composable
fun MainContent(
    navController: NavHostController,
    state: MainState,
    setEvent: KFunction1<MainEvent, Unit>,
    context: Context
) {
    DenkoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            if (state.permissionsEnabled) Navigation(navController, state.isUserSet)
            else PermissionScreen { setEvent(MainEvent.RedirectToPermissions(context)) }
        }
    }
}