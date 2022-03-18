package com.egoriku.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF1F0318),
    secondary = Color(0xFF8C705F),
    surface = Color(0xFF1E1A1D)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF1F0318),
    secondary = Color(0xFF8C705F),
    surface = Color.White
)

@Composable
fun LadyHappyAdminTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when {
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}