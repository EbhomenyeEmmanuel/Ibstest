package com.emmanull.ibstest.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.emmanull.ibstest.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.emmanull.ibstest.domain.model.LoginUiState
import com.emmanull.ibstest.navigation.Route
import com.emmanull.ibstest.ui.components.IbsButton
import com.emmanull.ibstest.ui.components.IbsTextField
import com.emmanull.ibstest.utils.shortToast

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginScreenRoute(
    onNavigate: (String) -> Unit,
    viewModel: LoginViewModel = viewModel(
    )
) {
    val uiState by viewModel.loginUiState.collectAsStateWithLifecycle()

    LoginScreen(
        uiState, onNavigate, doLogin = { email, password ->
            viewModel.login(email, password)
        }, onSuccess =
        viewModel::onSuccessShown
    )

}

@Composable
private fun LoginScreen(
    uiState: LoginUiState,
    navigate: (String) -> Unit,
    doLogin: (String, String) -> Unit,
    onSuccess: () -> Unit
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    DisposableEffect(key1 = uiState) {
        if (uiState.emailErrorMessage?.isEmpty() == true && uiState.passwordErrorMessage?.isEmpty() == true
            && !uiState.isEmailError && !uiState.isPasswordError
        ) {
            navigate(Route.HomeRoute.route)
            onSuccess()
        }
        onDispose {
        }
    }

    with(uiState) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally,
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
                onTextChanged = { email = it },
                placeholder = { Text(text = stringResource(R.string.email)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                isError = isEmailError
            )

            if (emailErrorMessage?.isNotEmpty() == true) {
                Text(
                    text = emailErrorMessage,
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Start,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                )
            }

            IbsTextField(
                onTextChanged = {
                    password = it
                },
                keyboardType = KeyboardType.Password,
                placeholder = { Text(text = stringResource(R.string.password)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                isError = isPasswordError
            )

            if (passwordErrorMessage?.isNotEmpty() == true) {
                Text(
                    text = passwordErrorMessage,
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Start,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                )
            }

            IbsButton(
                onClick = {
                    doLogin(email, password)
                },
                text = stringResource(id = R.string.login),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
            )

        }
    }
}

