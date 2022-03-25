package theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.WindowState
import com.egoriku.theme.LadyHappyAdminTheme
import com.egoriku.utils.screen.LocalWindowState

@Composable
fun LadyHappyDesktopTheme(
    windowState: WindowState,
    content: @Composable () -> Unit
) {
    LadyHappyAdminTheme {
        CompositionLocalProvider(LocalWindowState provides windowState, content = content)
    }
}