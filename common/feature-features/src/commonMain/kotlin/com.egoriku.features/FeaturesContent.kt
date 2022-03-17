package com.egoriku.features

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FeaturesContent(featuresComponent: FeaturesComponent) {
    Text(text = "Features", modifier = Modifier.clickable { featuresComponent.onFeatureClick() })
}