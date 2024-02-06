package com.example.denko.ui.screen.info

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.denko.R
import com.example.denko.domain.model.User
import com.example.denko.ui.navigation.NavigationItem

@Composable
fun InfoScreen(navController: NavController, viewModel: InfoViewModel = hiltViewModel()) {
    val setEvent = viewModel::setEvent

    LaunchedEffect(viewModel.effectTag) {
        viewModel.event.collect {
            when (it) {
                is InfoEvent.SetupUser -> {
                    navController.navigate(
                        NavigationItem.Dashboard.route, NavOptions.Builder()
                            .setLaunchSingleTop(true)
                            .setPopUpTo(NavigationItem.Info.route, inclusive = true)
                            .build()
                    )
                }
            }
        }
    }

    InfoContent {
        setEvent(InfoEvent.SetupUser(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoContent(onFinish: (user: User) -> Unit) {
    val scrollState = rememberScrollState()

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .scrollable(scrollState, Orientation.Vertical),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.1f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(text = stringResource(id = R.string.information))
                }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(text = stringResource(id = R.string.name))
                    },
                    shape = RoundedCornerShape(16.dp)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = surname,
                    onValueChange = { surname = it },
                    label = {
                        Text(text = stringResource(id = R.string.surname))
                    },
                    shape = RoundedCornerShape(16.dp)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = phone,
                    onValueChange = { phone = it },
                    label = {
                        Text(text = stringResource(id = R.string.phone_number))
                    },
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(modifier = Modifier.fillMaxSize(.1f))
            }

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
                shape = RectangleShape,
                onClick = {
                    onFinish(User("", "", ""))
                }
            ) {
                Text(text = stringResource(id = R.string.finish))
            }
        }
    }
}