package com.example.denko.ui.screen.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.denko.ui.composable.dialog.DialogWithButtons
import com.example.denko.ui.navigation.NavigationItem

@Composable
fun DashboardScreen(navController: NavController) {
    DashboardContent {
        navController.navigate(
            NavigationItem.Info.route, NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(NavigationItem.Dashboard.route, inclusive = true)
                .build()
        )
    }
}

@Composable
fun DashboardContent(onEditClicked: () -> Unit) {
    DialogWithButtons(
        onDismissRequest = { /*TODO*/ },
        onConfirmation = { /*TODO*/ },
        dialogTitle = "",
        dialogText = "",
        icon = Icons.Rounded.Edit
    )

    Column {
        Row(
            modifier = Modifier
                .fillMaxHeight(.1f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onEditClicked) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = "")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f),
            contentAlignment = Alignment.Center
        ) {

            OutlinedButton(
                onClick = {

                },
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
            ) {
                Text(text = "Help!", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}