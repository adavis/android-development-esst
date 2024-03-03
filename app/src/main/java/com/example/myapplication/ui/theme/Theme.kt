package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = ColorPrimaryDark,
    secondary = ColorAccent,
    tertiary = ColorGrey,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = ColorPrimary,
    secondary = ColorAccent,
    tertiary = ColorGrey,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
