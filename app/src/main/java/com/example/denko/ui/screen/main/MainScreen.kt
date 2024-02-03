package com.example.denko.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.denko.ui.navigation.Navigation
import com.example.denko.ui.theme.DenkoTheme

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    MainContent(navController = navController)
}

@Composable
fun MainContent(
//    state: MainState,
//    sendEvent: (MainEvent) -> Unit,
    navController: NavHostController
) {
    DenkoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigation(navController)
        }
    }
}