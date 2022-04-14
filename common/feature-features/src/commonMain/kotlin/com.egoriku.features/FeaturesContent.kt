package com.egoriku.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FeaturesContent(featuresComponent: FeaturesComponent) {
    val state by featuresComponent.model.collectAsState(FeaturesComponent.Model())

    LazyColumn {
        items(state.features) {
            Text(text = it.sheetName)
        }
    }

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