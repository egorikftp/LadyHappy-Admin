package com.egoriku.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.egoriku.main.MainContent
import com.egoriku.root.RootComponent.Child

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootContent(rootComponent: RootComponent) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Children(routerState = rootComponent.routerState, animation = crossfadeScale()) {
            when (val child = it.instance) {
                is Child.Main -> MainContent(component = child.component)
                //is Child.Login -> ConfigContent(component = child.component)
            }
        }
    }
}