package com.example.clase5.ui.viewmodel

import com.example.clase5.data.local.entity.UserEntity

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val validationError: LoginValidationError? = null,
    val currentUser: UserEntity? = null,
    val isUserSaved: Boolean = false,
    val searchResult: String? = null,
    val isLoading: Boolean = false,
)

enum class LoginValidationError {
    USERNAME_REQUIRED,
    PASSWORD_REQUIRED,
}
