package com.egoriku.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FeaturesContent(featuresComponent: FeaturesComponent) {
    Box(modifier = Modifier.fillMaxSize()) {
        FeatureCard(
            modifier = Modifier.align(Alignment.Center),
            name = "Config",
            onClick = featuresComponent::onFeatureClick
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeatureCard(modifier: Modifier, name: String, onClick: () -> Unit) {
    Card(modifier = modifier.fillMaxSize(fraction = 0.5f), onClick = onClick, elevation = 10.dp) {
        Box {
            Text(
                text = name,
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold
            )
        }
    }
}