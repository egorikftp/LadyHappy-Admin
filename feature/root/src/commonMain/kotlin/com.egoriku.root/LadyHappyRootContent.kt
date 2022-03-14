package com.egoriku.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.egoriku.root.LadyHappyRoot.LadyHappyChild
import com.egoriku.root.config.ConfigContent
import com.egoriku.root.main.MainScreenContent

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun LadyHappyRootContent(rootComponent: LadyHappyRoot) {
    Children(routerState = rootComponent.routerState, animation = crossfadeScale()) {
        when (val child = it.instance) {
            is LadyHappyChild.Main -> MainScreenContent(component = child.component)
            is LadyHappyChild.Config -> ConfigContent(component = child.component)
        }
    }
}