package com.emmanull.ibstest.domain.model

data class LoginUiState(
    val isLoading: Boolean = false,
    val isEmailError: Boolean = false,
    val emailErrorMessage: String? = null,
    val isPasswordError: Boolean = false,
    val passwordErrorMessage: String? = null,
)

