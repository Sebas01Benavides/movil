package com.example.clase5.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.clase5.data.local.DatabaseProvider
import com.example.clase5.data.local.entity.UserEntity
import com.example.clase5.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    application: Application,
) : AndroidViewModel(application) {

    private val repository: UserRepository = UserRepository(
        DatabaseProvider.getDatabase(application).userDao()
    )

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

    fun saveUser() {
        val email = _uiState.value.username
        val password = _uiState.value.password

        if (email.isBlank() || password.isBlank()) {
            validateInput()
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val user = UserEntity(email = email, passwordHash = password)
            repository.insertUser(user)
            _uiState.update {
                it.copy(
                    isUserSaved = true,
                    currentUser = user,
                    isLoading = false,
                    searchResult = "Usuario guardado exitosamente: $email"
                )
            }
        }
    }

    fun findUserByEmail(email: String = _uiState.value.username) {
        if (email.isBlank()) {
            _uiState.update { it.copy(validationError = LoginValidationError.USERNAME_REQUIRED) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val user = repository.findByEmail(email)
            _uiState.update {
                it.copy(
                    currentUser = user,
                    searchResult = if (user != null) {
                        "Usuario encontrado: ${user.email} (ID: ${user.id})"
                    } else {
                        "No se encontró usuario con el email: $email"
                    },
                    isLoading = false
                )
            }
        }
    }
}
