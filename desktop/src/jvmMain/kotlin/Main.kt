import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.egoriku.root.RootComponentImpl
import com.egoriku.root.RootContent
import com.egoriku.root.koin.initKoin
import com.egoriku.theme.LadyHappyAdminTheme

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    initKoin()

    val lifecycle = LifecycleRegistry()
    val rootComponent = RootComponentImpl(
        componentContext = DefaultComponentContext(lifecycle)
    )

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            title = "LadyHappy Admin Console",
        ) {
            LadyHappyAdminTheme {
                RootContent(rootComponent = rootComponent)
            }
        }
    }
}
