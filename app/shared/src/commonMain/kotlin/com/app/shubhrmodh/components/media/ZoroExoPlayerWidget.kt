package com.app.shubhrmodh.components.media

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.theme.PortfolioTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private val ZoroGreen       = Color(0xFF39FF14)
private val ZoroGreenDim    = Color(0xFF1A7A0A)
private val ZoroGreenGlow   = Color(0x6039FF14)
private val ZoroDark        = Color(0xFF050F05)
private val ZoroDarkSurface = Color(0xFF0A180A)
private val ZoroBorder      = Color(0x5039FF14)

private fun degreesToRadians(deg: Double): Double = deg * PI / 180.0

@Composable
fun ZoroExoPlayerWidget(
    onOpenPlayer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val infinite = rememberInfiniteTransition(label = "ZoroEffect")

    val orbPulse by infinite.animateFloat(
        initialValue = 0.6f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1800, easing = FastOutSlowInEasing), RepeatMode.Reverse
        ), label = "OrbPulse"
    )

    val slash1 by infinite.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1400, easing = LinearEasing), RepeatMode.Restart
        ), label = "Slash1"
    )
    val slash2 by infinite.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(1900, easing = LinearEasing, delayMillis = 400), RepeatMode.Restart
        ), label = "Slash2"
    )
    val slash3 by infinite.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(2300, easing = LinearEasing, delayMillis = 800), RepeatMode.Restart
        ), label = "Slash3"
    )

    val ringRotation by infinite.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(6000, easing = LinearEasing), RepeatMode.Restart
        ), label = "Ring"
    )

    val eq1 by infinite.animateFloat(
        initialValue = 0.3f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(380, easing = LinearEasing), RepeatMode.Reverse),
        label = "EQ1"
    )
    val eq2 by infinite.animateFloat(
        initialValue = 0.9f, targetValue = 0.2f,
        animationSpec = infiniteRepeatable(tween(290, easing = LinearEasing), RepeatMode.Reverse),
        label = "EQ2"
    )
    val eq3 by infinite.animateFloat(
        initialValue = 0.5f, targetValue = 0.95f,
        animationSpec = infiniteRepeatable(tween(440, easing = LinearEasing), RepeatMode.Reverse),
        label = "EQ3"
    )
    val eq4 by infinite.animateFloat(
        initialValue = 0.7f, targetValue = 0.25f,
        animationSpec = infiniteRepeatable(tween(330, easing = LinearEasing), RepeatMode.Reverse),
        label = "EQ4"
    )
    val eq5 by infinite.animateFloat(
        initialValue = 0.4f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(500, easing = LinearEasing), RepeatMode.Reverse),
        label = "EQ5"
    )
    val eqBars = listOf(eq1, eq2, eq3, eq4, eq5)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(148.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(ZoroDark)
            .border(1.dp, ZoroBorder, RoundedCornerShape(20.dp))
            .clickable(onClick = onOpenPlayer)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            drawRect(
                brush = Brush.radialGradient(
                    colors = listOf(ZoroDarkSurface, ZoroDark),
                    center = Offset(w * 0.72f, h * 0.5f),
                    radius = w * 0.6f
                )
            )

            val orbCx = w * 0.75f
            val orbCy = h * 0.5f
            val orbRadius = h * 0.6f * orbPulse
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(ZoroGreenGlow, Color.Transparent),
                    center = Offset(orbCx, orbCy),
                    radius = orbRadius
                ),
                center = Offset(orbCx, orbCy),
                radius = orbRadius
            )

            rotate(ringRotation, pivot = Offset(orbCx, orbCy)) {
                for (i in 0 until 12) {
                    val a1 = degreesToRadians((i * 30).toDouble())
                    val a2 = degreesToRadians(((i * 30) + 10).toDouble())
                    val r  = h * 0.38f
                    drawLine(
                        color = ZoroGreenDim,
                        start = Offset(orbCx + (r * cos(a1)).toFloat(), orbCy + (r * sin(a1)).toFloat()),
                        end   = Offset(orbCx + (r * cos(a2)).toFloat(), orbCy + (r * sin(a2)).toFloat()),
                        strokeWidth = 1.5f,
                        cap = StrokeCap.Round
                    )
                }
            }

            drawSwordSlash(w, h, t = slash1, yFraction = 0.30f, angleDeg = -28f, alpha = 0.90f)
            drawSwordSlash(w, h, t = slash2, yFraction = 0.52f, angleDeg = -22f, alpha = 0.75f)
            drawSwordSlash(w, h, t = slash3, yFraction = 0.72f, angleDeg = -32f, alpha = 0.55f)

            drawSantoryuSilhouette(orbCx, orbCy, h)
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(ZoroGreenGlow)
                        .border(1.dp, ZoroBorder, RoundedCornerShape(4.dp))
                        .padding(horizontal = 7.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "EXOPLAYER  •  MEDIA3",
                        color = ZoroGreen,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "三刀流 Engine",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Santōryū · Cursed Blade Arc",
                    color = ZoroGreen.copy(alpha = 0.7f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.height(16.dp)
                ) {
                    eqBars.forEach { v ->
                        Box(
                            modifier = Modifier
                                .width(3.dp)
                                .height((16 * v).dp)
                                .clip(RoundedCornerShape(1.dp))
                                .background(ZoroGreen)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "MediaSource · CacheDataSource · AudioSink",
                    color = Color.White.copy(alpha = 0.35f),
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 0.3.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.width(56.dp)
            ) {
                Text(
                    text = "▶",
                    color = ZoroGreen,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Open\nPlayer",
                    color = ZoroGreen.copy(alpha = 0.6f),
                    fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace,
                    lineHeight = 12.sp
                )
            }
        }
    }
}

private fun DrawScope.drawSwordSlash(
    w: Float,
    h: Float,
    t: Float,
    yFraction: Float,
    angleDeg: Float,
    alpha: Float,
) {
    val slashLen = w * 0.45f
    val trail    = w * 0.30f

    val tipX  = -slashLen + t * (w + slashLen * 2f)
    val baseY = h * yFraction
    val rad   = degreesToRadians(angleDeg.toDouble())
    val cosA  = cos(rad).toFloat()
    val sinA  = sin(rad).toFloat()

    val tipEnd   = Offset(tipX, baseY)
    val tipStart = Offset(tipX - slashLen * cosA, baseY - slashLen * sinA)
    val trailEnd = Offset(tipX - (slashLen + trail) * cosA, baseY - (slashLen + trail) * sinA)

    drawLine(
        brush = Brush.linearGradient(
            colors = listOf(Color.Transparent, ZoroGreen.copy(alpha = alpha * 0.8f)),
            start = trailEnd, end = tipStart
        ),
        start = trailEnd, end = tipStart,
        strokeWidth = 2.5f, cap = StrokeCap.Round
    )

    drawLine(
        brush = Brush.linearGradient(
            colors = listOf(ZoroGreen.copy(alpha = alpha), Color.White.copy(alpha = alpha * 0.6f)),
            start = tipStart, end = tipEnd
        ),
        start = tipStart, end = tipEnd,
        strokeWidth = 3.5f, cap = StrokeCap.Round
    )

    drawCircle(color = ZoroGreen.copy(alpha = alpha * 0.9f), center = tipEnd, radius = 3.5f)
    drawCircle(color = Color.White.copy(alpha = alpha * 0.5f), center = tipEnd, radius = 1.5f)
}

private fun DrawScope.drawSantoryuSilhouette(cx: Float, cy: Float, h: Float) {
    val swords = listOf(
        Triple(-10f, -h * 0.38f, -55.0),
        Triple(0f,   -h * 0.42f,   0.0),
        Triple(10f,  -h * 0.38f,  55.0),
    )

    swords.forEach { (dx, bladeLen, angleDeg) ->
        val rad    = degreesToRadians(angleDeg)
        val startX = cx + dx
        val startY = cy + h * 0.12f
        val endX   = startX + (bladeLen * sin(rad)).toFloat()
        val endY   = startY + (bladeLen * cos(rad)).toFloat()

        drawLine(
            color = ZoroGreen.copy(alpha = 0.55f),
            start = Offset(startX, startY),
            end   = Offset(endX, endY),
            strokeWidth = 2f,
            cap = StrokeCap.Round
        )

        val perpRad   = rad + PI / 2.0
        val guardCx   = startX + (bladeLen * 0.08f * sin(rad)).toFloat()
        val guardCy   = startY + (bladeLen * 0.08f * cos(rad)).toFloat()
        val guardHalf = h * 0.045f
        drawLine(
            color = ZoroGreenDim.copy(alpha = 0.8f),
            start = Offset(guardCx - (guardHalf * cos(perpRad)).toFloat(), guardCy - (guardHalf * sin(perpRad)).toFloat()),
            end   = Offset(guardCx + (guardHalf * cos(perpRad)).toFloat(), guardCy + (guardHalf * sin(perpRad)).toFloat()),
            strokeWidth = 2.5f,
            cap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ZoroExoPlayerWidgetPreview() {
    PortfolioTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            ZoroExoPlayerWidget(onOpenPlayer = {})
        }
    }
}
