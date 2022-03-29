package theme

import androidx.compose.runtime.*
import androidx.compose.ui.window.WindowState
import com.egoriku.theme.LadyHappyAdminTheme
import com.egoriku.utils.screen.LocalWindowState
import com.jthemedetecor.OsThemeDetector
import org.jetbrains.skiko.SystemTheme
import org.jetbrains.skiko.currentSystemTheme

@Composable
fun LadyHappyDesktopTheme(
    windowState: WindowState,
    content: @Composable () -> Unit
) {
    val darkTheme = rememberDesktopDarkTheme()

    LadyHappyAdminTheme(darkTheme = darkTheme) {
        CompositionLocalProvider(LocalWindowState provides windowState, content = content)
    }
}

@Composable
fun rememberDesktopDarkTheme(): Boolean {
    var darkTheme by remember {
        mutableStateOf(currentSystemTheme == SystemTheme.DARK)
    }

    DisposableEffect(Unit) {
        val darkThemeListener: (Boolean) -> Unit = {
            darkTheme = it
        }

        val detector = OsThemeDetector.getDetector().apply {
            registerListener(darkThemeListener)
        }

        onDispose {
            detector.removeListener(darkThemeListener)
        }
    }

    return darkTheme
}