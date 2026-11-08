package com.app.shubhrmodh.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BgDark = Color(0xFF090D1A)
val CardDark = Color(0xFF111827)
val CardDarkElevated = Color(0xFF162036)
val BorderDark = Color(0xFF1E293B)
val BorderGlow = Color(0xFF38BDF8).copy(alpha = 0.25f)

val PrimaryCyan = Color(0xFF38BDF8)
val PrimaryBlue = Color(0xFF0284C7)
val AccentIndigo = Color(0xFF818CF8)
val AccentEmerald = Color(0xFF10B981)

val TextPrimaryDark = Color(0xFFF8FAFC)
val TextSecondaryDark = Color(0xFF94A3B8)
val TextMutedDark = Color(0xFF64748B)

private val DarkColors = darkColorScheme(
    primary = PrimaryCyan,
    onPrimary = Color(0xFF090D1A),
    primaryContainer = Color(0xFF0C4A6E),
    onPrimaryContainer = Color(0xFFBAE6FD),
    secondary = AccentIndigo,
    onSecondary = Color(0xFF090D1A),
    background = BgDark,
    onBackground = TextPrimaryDark,
    surface = CardDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = CardDarkElevated,
    onSurfaceVariant = TextSecondaryDark,
    outline = BorderDark
)

private val LightColors = darkColorScheme(
    primary = PrimaryCyan,
    onPrimary = Color(0xFF090D1A),
    background = BgDark,
    onBackground = TextPrimaryDark,
    surface = CardDark,
    onSurface = TextPrimaryDark,
    outline = BorderDark
)

@Composable
fun PortfolioTheme(
    darkTheme: Boolean = true, content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColors, content = content
    )
}
