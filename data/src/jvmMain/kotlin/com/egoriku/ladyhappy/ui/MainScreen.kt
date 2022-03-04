package com.egoriku.ladyhappy.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.egoriku.getScreenModel
import com.egoriku.ladyhapppy.data.data.model.Config
import com.egoriku.ladyhapppy.data.data.util.calladapter.flow.Resource

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val contentScreenModel = getScreenModel<ContentScreenModel>()

        val configState by contentScreenModel.config.collectAsState()

        when (configState) {
            is Resource.Loading -> {
                Text("Loading")
            }
            is Resource.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    val items = (configState as Resource.Success<List<Config>>).data

                    items(items) {
                        Text(it.categoryName.toString())
                    }
                }
            }
            is Resource.Error -> {
                Text("Error")
            }
        }
    }
}
