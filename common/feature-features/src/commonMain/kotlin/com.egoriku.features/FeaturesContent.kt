package com.egoriku.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.egoriku.imageloader.remoteImagePainter
import com.egoriku.utils.screen.WindowSize
import com.egoriku.utils.screen.rememberWindowSize

@OptIn(ExperimentalMaterialApi::class)
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
        columns = GridCells.Fixed(gridCount),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(state.features) {
            Card(modifier = Modifier.padding(16.dp), elevation = 4.dp, onClick = {}) {
                Column(
                    modifier = Modifier.padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (it.iconUrl.isNotEmpty()) {
                        Image(
                            modifier = Modifier
                                .height(48.dp)
                                .width(48.dp),
                            painter = remoteImagePainter(it.iconUrl),
                            contentDescription = ""
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = it.sheetName)
                }
            }
        }
    }
}