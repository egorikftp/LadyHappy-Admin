package com.egoriku.ladyhappy.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun App() {
    MaterialTheme {
        BoxWithConstraints {
            Navigator(MainScreen())
        }
    }
}