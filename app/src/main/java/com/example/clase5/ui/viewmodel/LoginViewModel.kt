package com.example.clase5.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUsernameChange(username: String) {
        _uiState.update { currentState -> currentState.copy(username = username) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState -> currentState.copy(password = password) }
    }

    fun validateInput() {
        val (username, password) = _uiState.value
        val validationError = when {
            username.isBlank() -> LoginValidationError.USERNAME_REQUIRED
            password.isBlank() -> LoginValidationError.PASSWORD_REQUIRED
            else -> null
        }
        _uiState.update {
            it.copy(validationError = validationError)
        }
    }
}
