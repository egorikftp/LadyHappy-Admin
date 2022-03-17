package com.egoriku.ladyhappy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.egoriku.root.RootComponentImpl
import com.egoriku.root.RootContent
import com.egoriku.theme.LadyHappyAdminTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = RootComponentImpl(defaultComponentContext())

        setContent {
            LadyHappyAdminTheme {
                RootContent(rootComponent = rootComponent)
            }
        }
    }
}