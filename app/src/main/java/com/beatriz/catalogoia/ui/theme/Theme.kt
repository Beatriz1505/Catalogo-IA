package com.beatriz.catalogoia.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Background = Color(0xFF0B1020)
private val Surface = Color(0xFF141B2D)
private val Primary = Color(0xFF7C4DFF)
private val Secondary = Color(0xFF00C2FF)
private val Text = Color(0xFFF4F2FF)
private val Muted = Color(0xFFB7B8C8)

private val DarkColors = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    background = Background,
    surface = Surface,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Text,
    onSurface = Text
)

@Composable
fun CatalogoIATheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        content = content
    )
}
