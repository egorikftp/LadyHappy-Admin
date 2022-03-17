package com.egoriku.main

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.egoriku.config.ConfigContent
import com.egoriku.features.FeaturesContent
import com.egoriku.main.MainComponent.Child.*

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun MainContent(component: MainComponent) {
    Children(routerState = component.routerState, animation = crossfadeScale()) {
        when (val child = it.instance) {
            is Config -> ConfigContent(child.component)
            is Features -> FeaturesContent(child.component)
        }
    }
}