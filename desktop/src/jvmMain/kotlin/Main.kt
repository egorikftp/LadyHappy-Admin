import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.egoriku.ladyhapppy.data.koin.dataModule
import com.egoriku.ladyhapppy.data.koin.initKoin
import com.egoriku.ladyhappy.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "LadyHappy-Admin",
    ) {
        initKoin {
            modules(dataModule)
        }

        App()
    }
}
