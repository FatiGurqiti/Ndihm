package com.example.denko.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.denko.ui.screen.info.DashboardScreen
import com.example.denko.ui.screen.info.InfoScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.Info.route) {
        composable(NavigationItem.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        composable(NavigationItem.Info.route) {
            InfoScreen(navController = navController)
        }
    }
}