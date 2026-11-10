package com.example.clase5.ui.screens

import androidx.compose.ui.res.stringResource
import com.example.clase5.R

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clase5.ui.theme.Clase5Theme
import com.example.clase5.ui.viewmodel.LoginViewModel
import com.example.clase5.ui.viewmodel.LoginUiState
import com.example.clase5.ui.viewmodel.LoginValidationError

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LoginScreen(
        modifier = modifier,
        uiState = uiState,
        onUsernameChange = viewModel::onUsernameChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::validateInput,
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState = LoginUiState(),
    onUsernameChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onLoginClick: () -> Unit = {},
) {
    val errorMessage = when (uiState.validationError) {
        LoginValidationError.USERNAME_REQUIRED -> stringResource(R.string.login_username_required)
        LoginValidationError.PASSWORD_REQUIRED -> stringResource(R.string.login_password_required)
        null -> null
    }

    val successMessage = stringResource(R.string.login_success)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(
            12.dp,
            Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = stringResource(R.string.login_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Card(
            shape = RoundedCornerShape(6.dp),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 6.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = uiState.username,
                    onValueChange = onUsernameChange,
                    label = {
                        Text(
                            text = stringResource(R.string.login_username_label),
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(size = 8.dp),
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    label = {
                        Text(
                            text = stringResource(R.string.login_password_label),
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(size = 8.dp),
                    modifier = Modifier.fillMaxWidth(),
                )

                Button(
                    onClick = onLoginClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(size = 8.dp),
                ) {
                    Text(
                        text = stringResource(R.string.login_button),
                    )
                }

                when {
                    errorMessage != null -> {
                        Text(
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    ((uiState.validationError == null) && uiState.username.isNotBlank() && uiState.password.isNotBlank()) -> {
                        Text(
                            text = successMessage,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Clase5Theme {
        LoginScreen()
    }
}
