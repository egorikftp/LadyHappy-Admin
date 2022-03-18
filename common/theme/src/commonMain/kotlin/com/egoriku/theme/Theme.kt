package com.egoriku.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val RealBlack = Color(0xFF000000)
val RealWhite = Color(0xFFFFFFFF)
val Red = Color(0xFFB00020)
val RoseTaupe = Color(0xFF9C5b57)
val RoseTaupeDark = Color(0xFF704643)
val Shark = Color(0xFF272D33)
val Terracota = Color(0xFF7f6854)
val TerracotaDark = Color(0xFF655343)
val Westar = Color(0xFFE5E1DD)

private val DarkColorPalette = darkColors(
    primary = RoseTaupe,
    primaryVariant = RoseTaupeDark,
    onPrimary = Westar,
    secondary = Terracota,
    secondaryVariant = TerracotaDark,
    onSecondary = Shark,
    background = Shark,
    onBackground = Westar,
    surface = Shark,
    onSurface = Westar,
    error = Red,
    onError = Shark
)

private val LightColorPalette = lightColors(
    primary = RoseTaupe,
    primaryVariant = RoseTaupeDark,
    onPrimary = RealWhite,
    secondary = Terracota,
    secondaryVariant = TerracotaDark,
    onSecondary = RealBlack,
    background = RealWhite,
    onBackground = RealBlack,
    surface = RealWhite,
    onSurface = RealBlack,
    error = Red,
    onError = RealBlack
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