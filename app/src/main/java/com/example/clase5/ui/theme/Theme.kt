package com.example.clase5.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = HvacDarkPrimary,
    background = HvacDarkBackground,
    surface = HvacDarkSurface,
    onBackground = HvacDarkText,
    onSurface = HvacDarkText
)

private val LightColorScheme = lightColorScheme(
    primary = HvacBlue,
    secondary = HvacLightBlue,
    tertiary = HvacCyan,
    background = HvacBackground,
    surface = HvacSurface,
    surfaceVariant = HvacCardBackground,
    onPrimary = HvacTextLight,
    onSecondary = HvacTextLight,
    onTertiary = HvacTextDark,
    onBackground = HvacTextDark,
    onSurface = HvacTextDark
)

@Composable
fun Clase5Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}