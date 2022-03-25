@file:JvmName("WindowSizeJvm")

package com.egoriku.utils.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.window.WindowState

val LocalWindowState = staticCompositionLocalOf { WindowState() }

@Composable
actual fun rememberWindowSize(): WindowSize {
    val windowState = LocalWindowState.current

    return getWindowSizeClass(windowDpSize = windowState.size)
}