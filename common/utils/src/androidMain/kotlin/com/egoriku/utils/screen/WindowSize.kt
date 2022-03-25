@file:JvmName("WindowSizeAndroid")

package com.egoriku.utils.screen

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.window.layout.WindowMetricsCalculator

@Composable
actual fun rememberWindowSize(): WindowSize {
    val current = LocalContext.current as Activity
    val windowSize = current.rememberWindowSize()

    val windowDpSize = with(LocalDensity.current) {
        windowSize.toDpSize()
    }

    return getWindowSizeClass(windowDpSize)
}

/**
 * Remembers the [Size] in pixels of the window corresponding to the current window metrics.
 */
@Composable
private fun Activity.rememberWindowSize(): Size {
    val configuration = LocalConfiguration.current

    val windowMetrics = remember(configuration) {
        WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
    }
    return windowMetrics.bounds.toComposeRect().size
}