package com.egoriku.features

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.egoriku.utils.screen.WindowSize
import com.egoriku.utils.screen.rememberWindowSize

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun FeaturesContent(featuresComponent: FeaturesComponent) {
    val state by featuresComponent.model.collectAsState(FeaturesComponent.Model())

    val gridCount = when (rememberWindowSize()) {
        WindowSize.Compact -> 1
        WindowSize.Medium -> 2
        WindowSize.Expanded -> 3
    }

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        cells = GridCells.Fixed(gridCount),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(state.features) {
            Card(modifier = Modifier.padding(16.dp), elevation = 4.dp, onClick = {}) {
                Column(modifier = Modifier.padding(40.dp)) {
                    Text(text = it.sheetName)
                }
            }
        }
    }
}