package com.egoriku.imageloader

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberAsyncImagePainter

@Composable
actual fun remoteImagePainter(url: String): Painter = rememberAsyncImagePainter(url)