import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.egoriku.root.LadyHappyRootComponent
import com.egoriku.root.LadyHappyRootContent
import com.egoriku.root.koin.initKoin

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    initKoin()

    val lifecycle = LifecycleRegistry()
    val rootComponent = LadyHappyRootComponent(
        componentContext = DefaultComponentContext(lifecycle)
    )

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            title = "LadyHappy-Admin",
        ) {
            MaterialTheme(
                colors = MaterialTheme.colors,
                typography = MaterialTheme.typography,
                shapes = MaterialTheme.shapes
            ) {
                LadyHappyRootContent(rootComponent = rootComponent)
            }
        }
    }
}
