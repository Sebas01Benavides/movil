package com.example.clase5.ui.viewmodel

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val validationError: LoginValidationError? = null,
)

enum class LoginValidationError {
    USERNAME_REQUIRED,
    PASSWORD_REQUIRED,
}
