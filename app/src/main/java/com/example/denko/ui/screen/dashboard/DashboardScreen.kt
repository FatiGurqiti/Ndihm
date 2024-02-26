package com.example.denko.ui.screen.dashboard

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.denko.R
import com.example.denko.data.remote.firebase.RealtimeDataBaseHandler
import com.example.denko.ui.composable.dialog.DialogWithButtons
import com.example.denko.ui.navigation.NavigationItem
import javax.inject.Inject
import kotlin.reflect.KFunction1

@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel = hiltViewModel()) {
    val state: DashboardState by viewModel.state.collectAsStateWithLifecycle()
    val setEvent = viewModel::setEvent

    DashboardContent(state, setEvent, navController)
}

@Composable
fun DashboardContent(
    state: DashboardState, setEvent: KFunction1<DashboardEvent, Unit>, navController: NavController
) {
    val context = LocalContext.current

    if (state.confirmDialogVisibility) {
        DialogWithButtons(
            onDismissRequest = { setEvent(DashboardEvent.CloseConfirmDialog) },
            onConfirmation = { setEvent(DashboardEvent.HelpAction(context)) },
            dialogTitle = stringResource(id = R.string.confirmation),
            dialogText = stringResource(id = R.string.conformation_text),
            positiveText = stringResource(id = R.string.confirm),
            negativeText = stringResource(id = R.string.cancel)
        )
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxHeight(.1f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                enabled = !state.helpActionActive,
                onClick = {
                    navController.navigate(
                        NavigationItem.Info.route,
                        NavOptions.Builder().setLaunchSingleTop(true)
                            .setPopUpTo(NavigationItem.Dashboard.route, inclusive = true).build()
                    )
                }) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = "")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f), contentAlignment = Alignment.Center
        ) {

            OutlinedButton(
                onClick = { setEvent(DashboardEvent.OnHelpButtonClick(context)) },
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
            ) {
                val textId = if (state.helpActionActive) R.string.stop_call
                else R.string.help

                Text(
                    text = stringResource(id = textId),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}