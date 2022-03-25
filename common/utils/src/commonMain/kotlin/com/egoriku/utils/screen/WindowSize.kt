package com.egoriku.utils.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

enum class WindowSize { Compact, Medium, Expanded }

@Composable
expect fun rememberWindowSize(): WindowSize

/**
 * Partitions a [DpSize] into a enumerated [WindowSize] class.
 */
fun getWindowSizeClass(windowDpSize: DpSize): WindowSize = when {
    windowDpSize.width < 0.dp -> throw IllegalArgumentException("Dp value cannot be negative")
    windowDpSize.width < 600.dp -> WindowSize.Compact
    windowDpSize.width < 840.dp -> WindowSize.Medium
    else -> WindowSize.Expanded
}
