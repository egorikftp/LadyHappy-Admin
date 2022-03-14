package com.egoriku.root.config

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.egoriku.root.config.network.ConfigResult
import com.egoriku.root.config.network.ConfigViewModel
import com.egoriku.viewmodel.getViewModel

@Composable
fun ConfigContent(component: ConfigScreen) {
    val viewModel = getViewModel<ConfigViewModel>()


    val state by viewModel.resultStateFlow.collectAsState()

    DisposableEffect(viewModel) {
        viewModel.getConfig()
        onDispose(viewModel::onCleared)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(
            title = { Text("Config") },
            navigationIcon = {
                IconButton(onClick = component::onCloseClicked) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )

        when (state) {
            is ConfigResult.Loading -> Text("Loading")
            is ConfigResult.Success -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                val items = (state as ConfigResult.Success).data

                items(items) {
                    Text(it.toString())
                }
            }
            is ConfigResult.Error -> Text("Error")
        }
    }
}