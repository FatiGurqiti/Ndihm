package com.example.denko.ui.navigation

sealed class NavigationItem(
    val route: String,
    val title: String,
) {
    data object Info :
        NavigationItem("/info", "Info")

    data object Dashboard :
        NavigationItem("/dashboard", "Dashboard")
}