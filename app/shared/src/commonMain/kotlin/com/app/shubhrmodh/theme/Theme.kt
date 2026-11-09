package com.app.shubhrmodh.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalDarkTheme = staticCompositionLocalOf { true }

val RealPureBlack = Color(0xFF000000)
val RealPureWhite = Color(0xFFFFFFFF)

val PureBlack: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0xFF000000) else Color(0xFFFFFFFF)

val PureWhite: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0xFFFFFFFF) else Color(0xFF09090B)

val White90: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0xE6FFFFFF) else Color(0xE6000000)

val White70: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0xB3FFFFFF) else Color(0xB3000000)

val White50: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0x80FFFFFF) else Color(0x80000000)

val White30: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0x4DFFFFFF) else Color(0x4D000000)

val White15: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0x26FFFFFF) else Color(0x1F000000)

val White08: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0x14FFFFFF) else Color(0x0D000000)

val White05: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0x0DFFFFFF) else Color(0x08000000)

val Black90: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0xE6000000) else Color(0xE6FFFFFF)

val Black80: Color
    @Composable get() = if (LocalDarkTheme.current) Color(0xCC000000) else Color(0xCCFFFFFF)

val BorderWhite: Color
    @Composable get() = White15

val BorderWhiteBold: Color
    @Composable get() = White30

private val MonochromeDarkColors = darkColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFFFFFFFF),
    onPrimaryContainer = Color(0xFF000000),
    secondary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0x26FFFFFF),
    onSecondaryContainer = Color(0xFFFFFFFF),
    tertiary = Color(0xFFFFFFFF),
    onTertiary = Color(0xFF000000),
    background = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF000000),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0x14FFFFFF),
    onSurfaceVariant = Color(0xB3FFFFFF),
    outline = Color(0x26FFFFFF),
    outlineVariant = Color(0x14FFFFFF),
)

private val MonochromeLightColors = lightColorScheme(
    primary = Color(0xFF09090B),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF09090B),
    onPrimaryContainer = Color(0xFFFFFFFF),
    secondary = Color(0xFF09090B),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0x1F000000),
    onSecondaryContainer = Color(0xFF09090B),
    tertiary = Color(0xFF09090B),
    onTertiary = Color(0xFFFFFFFF),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF09090B),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF09090B),
    surfaceVariant = Color(0x0D000000),
    onSurfaceVariant = Color(0xB3000000),
    outline = Color(0x1F000000),
    outlineVariant = Color(0x0D000000),
)

@Composable
fun PortfolioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) MonochromeDarkColors else MonochromeLightColors
    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}
