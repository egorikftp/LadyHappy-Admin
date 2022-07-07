package com.egoriku.imageloader

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
expect fun remoteImagePainter(url: String): Painter
