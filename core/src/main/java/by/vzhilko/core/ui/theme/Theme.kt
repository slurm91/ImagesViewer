package by.vzhilko.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val lightColorScheme = lightColorScheme(
    primary = purple200,
    onPrimary = white,
    primaryContainer = purple700,
    onPrimaryContainer = white,
    secondary = teal200,
    onSecondary = black,
    surface = white,
    onSurface = black,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    //val colorScheme: ColorScheme = lightColorScheme
    MaterialTheme(
        content = content
    )
}