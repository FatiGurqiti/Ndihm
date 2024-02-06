package com.example.denko.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.denko.ui.screen.dashboard.DashboardScreen
import com.example.denko.ui.screen.info.InfoScreen

@Composable
fun Navigation(navController: NavHostController, isUserSet: Boolean) {
    val startDestination =
        if (isUserSet) NavigationItem.Dashboard.route else NavigationItem.Info.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        composable(NavigationItem.Info.route) {
            InfoScreen(navController = navController)
        }
    }
}