package com.emmanull.ibstest.ui.auth

import androidx.lifecycle.ViewModel
import com.emmanull.ibstest.domain.model.LoginUiState
import com.emmanull.ibstest.utils.isEmailValid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val TAG = javaClass.simpleName
    private val _uiState =
        MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        //_uiState.update { it.copy(isLoading = true) }

        if (email.isEmpty() || !email.isEmailValid()) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    isEmailError = true,
                    isPasswordError = false,
                    emailErrorMessage = "Invalid email format",
                    passwordErrorMessage = ""
                )
            }
        } else if (password.isEmpty() || password.length < 6) {
            _uiState.update {
                it.copy(
                    isPasswordError = true,
                    isEmailError = false,
                    passwordErrorMessage = "Invalid password format",
                    emailErrorMessage = "",
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    isEmailError = false,
                    isPasswordError = false,
                    emailErrorMessage = "",
                    passwordErrorMessage = ""
                )
            }
        }
    }

    fun onSuccessShown() {
        _uiState.update {
            it.copy(
                isEmailError = false,
                isPasswordError = false,
                isLoading = false,
                emailErrorMessage = "",
                passwordErrorMessage = ""
            )
        }
    }

}