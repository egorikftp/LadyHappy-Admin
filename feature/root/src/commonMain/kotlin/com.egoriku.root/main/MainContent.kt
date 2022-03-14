package com.egoriku.root.main

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreenContent(component: MainScreen) {
    Text(
        text = "Main content",
        modifier = Modifier.clickable { component.onFeatureClick(MainFeatures.Categories) })
}