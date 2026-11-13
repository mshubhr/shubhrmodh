package com.app.shubhrmodh.components.media

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

internal val ZoroGreen      = Color(0xFF39FF14)
private val ZoroGreenDim    = Color(0xFF1A7A0A)
internal val ZoroGreenGlow  = Color(0x6039FF14)
private val ZoroBorder      = Color(0x5039FF14)
private val ZoroDark        = Color(0xFF050F05)
private val ZoroDarkSurface = Color(0xFF0A180A)

private fun degreesToRadians(deg: Double): Double = deg * PI / 180.0

@Composable
fun ZoroBackground(modifier: Modifier = Modifier) {
    val inf = rememberInfiniteTransition(label = "ZoroBg")

    val orb1Pulse by inf.animateFloat(
        initialValue = 0.55f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(4000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "Orb1"
    )
    val orb2Pulse by inf.animateFloat(
        initialValue = 1f, targetValue = 0.5f,
        animationSpec = infiniteRepeatable(tween(6000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "Orb2"
    )
    val bgShift by inf.animateFloat(
        initialValue = 0.3f, targetValue = 0.75f,
        animationSpec = infiniteRepeatable(tween(12000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "BgShift"
    )
    val ringRot by inf.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(18000, easing = LinearEasing), RepeatMode.Restart),
        label = "Ring"
    )
    val wisp1 by inf.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(7000, easing = LinearEasing), RepeatMode.Restart),
        label = "W1"
    )
    val wisp2 by inf.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(9500, easing = LinearEasing, delayMillis = 2000), RepeatMode.Restart),
        label = "W2"
    )
    val wisp3 by inf.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(11000, easing = LinearEasing, delayMillis = 4500), RepeatMode.Restart),
        label = "W3"
    )
    val siloAlpha by inf.animateFloat(
        initialValue = 0.08f, targetValue = 0.22f,
        animationSpec = infiniteRepeatable(tween(5000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "SiloAlpha"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(ZoroDarkSurface, ZoroDark, Color.Black),
                center = Offset(w * bgShift, h * 0.4f),
                radius = w * 0.9f
            )
        )

        val o1x = w * 0.78f
        val o1y = h * 0.65f
        val o1r = h * 0.55f * orb1Pulse
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(ZoroGreenGlow, Color.Transparent),
                center = Offset(o1x, o1y),
                radius = o1r
            ),
            center = Offset(o1x, o1y),
            radius = o1r
        )

        val o2x = w * 0.18f
        val o2y = h * 0.22f
        val o2r = h * 0.35f * orb2Pulse
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(ZoroGreenDim.copy(alpha = 0.35f), Color.Transparent),
                center = Offset(o2x, o2y),
                radius = o2r
            ),
            center = Offset(o2x, o2y),
            radius = o2r
        )

        rotate(ringRot, pivot = Offset(o1x, o1y)) {
            for (i in 0 until 16) {
                val a1 = degreesToRadians((i * 22.5))
                val a2 = degreesToRadians((i * 22.5) + 7.0)
                val r  = h * 0.28f
                drawLine(
                    color = ZoroGreenDim.copy(alpha = 0.4f),
                    start = Offset(o1x + (r * cos(a1)).toFloat(), o1y + (r * sin(a1)).toFloat()),
                    end   = Offset(o1x + (r * cos(a2)).toFloat(), o1y + (r * sin(a2)).toFloat()),
                    strokeWidth = 1.2f,
                    cap = StrokeCap.Round
                )
            }
        }

        drawEnergyWisp(w, h, t = wisp1, yFrac = 0.28f, angleDeg = -18f, alpha = 0.28f, wide = true)
        drawEnergyWisp(w, h, t = wisp2, yFrac = 0.55f, angleDeg = -12f, alpha = 0.20f, wide = true)
        drawEnergyWisp(w, h, t = wisp3, yFrac = 0.78f, angleDeg = -22f, alpha = 0.15f, wide = true)

        drawSantoryuSilhouette(o1x, o1y, h, alpha = siloAlpha)
    }
}

private fun DrawScope.drawEnergyWisp(
    w: Float,
    h: Float,
    t: Float,
    yFrac: Float,
    angleDeg: Float,
    alpha: Float,
    wide: Boolean = false,
) {
    val len   = w * 0.65f
    val trail = w * 0.55f
    val thick = if (wide) 18f else 6f

    val tipX  = -len + t * (w + len * 2f)
    val baseY = h * yFrac
    val rad   = degreesToRadians(angleDeg.toDouble())
    val cosA  = cos(rad).toFloat()
    val sinA  = sin(rad).toFloat()

    val tipEnd    = Offset(tipX, baseY)
    val tipStart  = Offset(tipX - len * cosA, baseY - len * sinA)
    val trailEnd  = Offset(tipX - (len + trail) * cosA, baseY - (len + trail) * sinA)

    drawLine(
        brush = Brush.linearGradient(
            colors = listOf(Color.Transparent, ZoroGreen.copy(alpha = alpha * 0.6f)),
            start = trailEnd, end = tipStart
        ),
        start = trailEnd, end = tipStart,
        strokeWidth = thick, cap = StrokeCap.Round
    )
    drawLine(
        brush = Brush.linearGradient(
            colors = listOf(ZoroGreen.copy(alpha = alpha), ZoroGreen.copy(alpha = alpha * 0.3f)),
            start = tipStart, end = tipEnd
        ),
        start = tipStart, end = tipEnd,
        strokeWidth = thick * 0.6f, cap = StrokeCap.Round
    )
}

private fun DrawScope.drawSantoryuSilhouette(
    cx: Float, cy: Float, h: Float, alpha: Float
) {
    val swords = listOf(
        Triple(-14f, -h * 0.40f, -55.0),
        Triple(0f,   -h * 0.44f,   0.0),
        Triple(14f,  -h * 0.40f,  55.0),
    )
    swords.forEach { (dx, bladeLen, angleDeg) ->
        val rad    = degreesToRadians(angleDeg)
        val sx     = cx + dx
        val sy     = cy + h * 0.10f
        val ex     = sx + (bladeLen * sin(rad)).toFloat()
        val ey     = sy + (bladeLen * cos(rad)).toFloat()

        drawLine(
            color = ZoroGreen.copy(alpha = alpha),
            start = Offset(sx, sy), end = Offset(ex, ey),
            strokeWidth = 2.5f, cap = StrokeCap.Round
        )

        val perp      = rad + PI / 2.0
        val gx        = sx + (bladeLen * 0.08f * sin(rad)).toFloat()
        val gy        = sy + (bladeLen * 0.08f * cos(rad)).toFloat()
        val guardHalf = h * 0.04f
        drawLine(
            color = ZoroGreenDim.copy(alpha = alpha * 0.9f),
            start = Offset(gx - (guardHalf * cos(perp)).toFloat(), gy - (guardHalf * sin(perp)).toFloat()),
            end   = Offset(gx + (guardHalf * cos(perp)).toFloat(), gy + (guardHalf * sin(perp)).toFloat()),
            strokeWidth = 2.5f, cap = StrokeCap.Round
        )
    }
}
