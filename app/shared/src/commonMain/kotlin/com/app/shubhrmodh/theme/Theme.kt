package com.app.shubhrmodh.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalDarkTheme = staticCompositionLocalOf { true }

val AndroidDarkBg = Color(0xFF0B0F0D)
val AndroidCardDark = Color(0xFF121714)
val AndroidCardInner = Color(0xFF1A211D)
val AndroidTextWhite = Color(0xFFF0F2F0)
val AndroidGreenAccent = Color(0xFF3DDC84)
val AndroidGreenDarkContainer = Color(0xFF183325)
val AndroidSoftGrey = Color(0xFF9EA6A1)
val AndroidBorderDark = Color(0xFF233028)

val WarmOffWhite = AndroidDarkBg
val PureCardWhite = AndroidCardDark
val DeepCharcoal = AndroidTextWhite
val CobaltBlue = AndroidGreenAccent
val SoftGrey = AndroidSoftGrey
val SubtleBorderColor = AndroidBorderDark
val AccentBlueLight = AndroidGreenDarkContainer

val RealPureBlack = Color(0xFF000000)
val RealPureWhite = Color(0xFFFFFFFF)

val PureBlack: Color
    @Composable get() = AndroidDarkBg

val PureWhite: Color
    @Composable get() = AndroidTextWhite

val White90: Color
    @Composable get() = Color(0xE6F0F2F0)

val White70: Color
    @Composable get() = AndroidSoftGrey

val White50: Color
    @Composable get() = Color(0x809EA6A1)

val White30: Color
    @Composable get() = Color(0x4D9EA6A1)

val White15: Color
    @Composable get() = AndroidBorderDark

val White08: Color
    @Composable get() = AndroidCardDark

val White05: Color
    @Composable get() = AndroidGreenDarkContainer

val Black90: Color
    @Composable get() = AndroidDarkBg

val Black80: Color
    @Composable get() = AndroidCardInner

val BorderWhite: Color
    @Composable get() = AndroidBorderDark

val BorderWhiteBold: Color
    @Composable get() = Color(0xFF32453A)

private val AndroidGreenDarkColors = darkColorScheme(
    primary = AndroidGreenAccent,
    onPrimary = Color(0xFF0B0F0D),
    primaryContainer = AndroidGreenDarkContainer,
    onPrimaryContainer = AndroidGreenAccent,
    secondary = AndroidGreenAccent,
    onSecondary = Color(0xFF0B0F0D),
    secondaryContainer = AndroidGreenDarkContainer,
    onSecondaryContainer = AndroidTextWhite,
    tertiary = AndroidGreenAccent,
    onTertiary = Color(0xFF0B0F0D),
    background = AndroidDarkBg,
    onBackground = AndroidTextWhite,
    surface = AndroidCardDark,
    onSurface = AndroidTextWhite,
    surfaceVariant = AndroidCardInner,
    onSurfaceVariant = AndroidSoftGrey,
    outline = AndroidBorderDark,
    outlineVariant = AndroidBorderDark,
)

@Composable
fun PortfolioTheme(
    darkTheme: Boolean = true,  content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDarkTheme provides true
    ) {
        MaterialTheme(
            colorScheme = AndroidGreenDarkColors, content = content
        )
    }
}
