package com.app.shubhrmodh.components.motion

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun animateColorFast(
    targetColor: Color,
    durationMs: Int = 180
): State<Color> {
    return animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(durationMillis = durationMs),
        label = "ColorAnimation"
    )
}

fun Modifier.hoverScale(
    isHovered: Boolean,
    scale: Float = 1.025f,
    reduceMotion: Boolean = false
): Modifier = composed {
    val animatedScale by animateFloatAsState(
        targetValue = if (isHovered && !reduceMotion) scale else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "HoverScaleAnimation"
    )
    this.graphicsLayer {
        scaleX = animatedScale
        scaleY = animatedScale
    }
}

fun Modifier.pressScale(
    interactionSource: InteractionSource,
    scale: Float = 0.96f
): Modifier = composed {
    var isPressed by remember { mutableStateOf(false) }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> isPressed = true
                is PressInteraction.Release, is PressInteraction.Cancel -> isPressed = false
            }
        }
    }

    val animatedScale by animateFloatAsState(
        targetValue = if (isPressed) scale else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "PressScaleAnimation"
    )
    this.graphicsLayer {
        scaleX = animatedScale
        scaleY = animatedScale
    }
}

fun createAnimatedGreenMatrix(progress: Float): ColorFilter? {
    if (progress <= 0.001f) return null

    val p = progress.coerceIn(0f, 1f)

    // Blend identity -> GreenAccent (0x3D, 0xDC, 0x84)
    val targetR = 0.2392f / 3f
    val targetG = 0.8627f / 3f
    val targetB = 0.5176f / 3f

    // Standard identity channels
    val rR = (1f - p) * (1f / 3f) + p * targetR
    val rG = (1f - p) * (1f / 3f) + p * targetG
    val rB = (1f - p) * (1f / 3f) + p * targetB

    return ColorFilter.colorMatrix(
        ColorMatrix(
            floatArrayOf(
                rR, rR, rR, 0f, 0f,
                rG, rG, rG, 0f, 0f,
                rB, rB, rB, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
            )
        )
    )
}
