package com.emmanull.ibstest.ui.auth

import androidx.lifecycle.ViewModel
import com.emmanull.ibstest.utils.isEmailValid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface LoginUiState {
    object Empty : LoginUiState

    object Loading : LoginUiState

    data class Success(val message: String) : LoginUiState

    data class Error(
        val isEmailError: Boolean = false,
        val message: String = "",
        val isPasswordError: Boolean = false,
    ) : LoginUiState
}


class LoginViewModel : ViewModel() {
    private val TAG = javaClass.simpleName
    private val _uiState: MutableStateFlow<LoginUiState> =
        MutableStateFlow(LoginUiState.Empty)
    val loginUiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        _uiState.value = LoginUiState.Loading

        if (email.isEmpty() || !email.isEmailValid()) {
            _uiState.value = LoginUiState.Error(
                isEmailError = true,
                isPasswordError = false,
                message = "Invalid Email"
            )
            return
        }

        if (password.isEmpty() || password.length < 6) {
            _uiState.value =
                LoginUiState.Error(
                    isPasswordError = true,
                    isEmailError = false,
                    message = "Invalid Password"
                )
            return
        }

        _uiState.value = LoginUiState.Success("Success!")
    }

    fun onSuccessShown() {
        _uiState.value = LoginUiState.Empty
    }

}