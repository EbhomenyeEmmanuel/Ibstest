package com.emmanull.ibstest.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emmanull.ibstest.R
import com.emmanull.ibstest.navigation.Route
import com.emmanull.ibstest.ui.components.IbsButton
import com.emmanull.ibstest.ui.components.IbsTextField

@Composable
fun LoginScreenRoute(
    onNavigate: (String) -> Unit,
    viewModel: LoginViewModel = viewModel(
    )
) {
    LoginScreen(onNavigate)
}

@Composable
private fun LoginScreen(
    navigate: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 32.dp)
        )

        IbsTextField(
            onTextChanged = {},
            placeholder = { Text(text = stringResource(R.string.email)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        )

        IbsTextField(
            onTextChanged = {},
            placeholder = { Text(text = stringResource(R.string.password)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        IbsButton(
            onClick = {
                //navigate(Route.HomeRoute.route)
            },
            text = stringResource(id = R.string.login)
        )

    }
}

