package com.app.shubhrmodh.components.media

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.components.CustomIcons
import com.app.shubhrmodh.components.motion.MotionCollapsingHeader
import com.app.shubhrmodh.data.PortfolioRepository
import com.app.shubhrmodh.theme.BorderWhite
import com.app.shubhrmodh.theme.BorderWhiteBold
import com.app.shubhrmodh.theme.PureBlack
import com.app.shubhrmodh.theme.PureWhite
import com.app.shubhrmodh.theme.White08
import com.app.shubhrmodh.theme.White15
import com.app.shubhrmodh.theme.White30
import com.app.shubhrmodh.theme.White50
import com.app.shubhrmodh.theme.White70

@Composable
fun ExoPlayerScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tracks = PortfolioRepository.exoTracks

    var selectedTrackIndex by remember { mutableStateOf(0) }
    var isPlaying by remember { mutableStateOf(true) }
    var progressSeconds by remember { mutableStateOf(42f) }
    var playbackSpeed by remember { mutableStateOf("1.0x") }
    var showDiagnostics by remember { mutableStateOf(false) }

    val currentTrack = tracks[selectedTrackIndex]
    val scrollState = rememberScrollState()

    val infiniteTransition = rememberInfiniteTransition(label = "VinylSpin")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Rotation"
    )

    val barHeights = List(9) { index ->
        val duration = 300 + (index * 60)
        infiniteTransition.animateFloat(
            initialValue = 0.2f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(duration, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "EqualizerBar$index"
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PureBlack)
    ) {
        MotionCollapsingHeader(
            title = "ExoPlayer Media3",
            subtitle = "Android Audio & Video Player Engine",
            icon = CustomIcons.Disc,
            scrollOffset = scrollState.value,
            onBack = onBack,
            actionIcon = CustomIcons.Settings,
            onActionClick = { showDiagnostics = !showDiagnostics }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(White08)
                    .border(2.dp, PureWhite, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .border(1.dp, White30, CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(1.dp, White30, CircleShape)
                )

                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .rotate(if (isPlaying) rotation else 0f)
                        .clip(CircleShape)
                        .background(PureWhite),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "EXO",
                        color = PureBlack,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .height(36.dp)
                    .fillMaxWidth(0.6f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                barHeights.forEach { anim ->
                    val height = if (isPlaying) (36 * anim.value).dp else 4.dp
                    Box(
                        modifier = Modifier
                            .width(5.dp)
                            .height(height)
                            .clip(RoundedCornerShape(2.dp))
                            .background(PureWhite)
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = currentTrack.title,
                color = PureWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = currentTrack.subtitle,
                color = White70,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(White15)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .fillMaxHeight()
                            .background(White30)
                    )
                    val fraction = (progressSeconds / currentTrack.durationSec).coerceIn(0f, 1f)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction)
                            .fillMaxHeight()
                            .background(PureWhite)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val currentMin = (progressSeconds / 60).toInt()
                    val currentSec = (progressSeconds % 60).toInt()
                    val totalMin = currentTrack.durationSec / 60
                    val totalSec = currentTrack.durationSec % 60

                    Text(
                        text = "${currentMin}:${if (currentSec < 10) "0$currentSec" else "$currentSec"}",
                        color = White70,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "${totalMin}:${if (totalSec < 10) "0$totalSec" else "$totalSec"}",
                        color = White50,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, BorderWhite, RoundedCornerShape(8.dp))
                        .clickable {
                            playbackSpeed = when (playbackSpeed) {
                                "1.0x" -> "1.5x"
                                "1.5x" -> "2.0x"
                                "2.0x" -> "0.5x"
                                else -> "1.0x"
                            }
                        }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = playbackSpeed,
                        color = PureWhite,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                IconButton(
                    onClick = {
                        selectedTrackIndex = if (selectedTrackIndex > 0) selectedTrackIndex - 1 else tracks.size - 1
                        progressSeconds = 0f
                    }
                ) {
                    Icon(
                        imageVector = CustomIcons.SkipPrevious,
                        contentDescription = "Previous",
                        tint = PureWhite,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(PureWhite)
                        .clickable { isPlaying = !isPlaying },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPlaying) CustomIcons.Pause else CustomIcons.Play,
                        contentDescription = "Play/Pause",
                        tint = PureBlack,
                        modifier = Modifier.size(28.dp)
                    )
                }

                IconButton(
                    onClick = {
                        selectedTrackIndex = (selectedTrackIndex + 1) % tracks.size
                        progressSeconds = 0f
                    }
                ) {
                    Icon(
                        imageVector = CustomIcons.SkipNext,
                        contentDescription = "Next",
                        tint = PureWhite,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (showDiagnostics) PureWhite else White08)
                        .border(1.dp, BorderWhite, RoundedCornerShape(8.dp))
                        .clickable { showDiagnostics = !showDiagnostics }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "DIAG",
                        color = if (showDiagnostics) PureBlack else PureWhite,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (showDiagnostics) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderWhiteBold, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = White08),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "ExoPlayer / Media3 Diagnostics",
                            color = PureWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        val diagRows = listOf(
                            "Player Instance" to "androidx.media3.exoplayer.ExoPlayer",
                            "Playback State" to if (isPlaying) "STATE_READY" else "STATE_PAUSED",
                            "Audio Renderer" to "MediaCodecAudioRenderer (AAC)",
                            "Audio Sink" to "DefaultAudioSink (44.1 kHz, 16-bit)",
                            "Buffer Duration" to "2,500 ms (Cached in RAM)",
                            "16 KB Alignment" to "SDK 35 Verified (libexoplayer.so)"
                        )

                        diagRows.forEach { (label, value) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = label, color = White70, fontSize = 11.sp)
                                Text(
                                    text = value,
                                    color = PureWhite,
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Architecture Note: ${currentTrack.techNote}",
                            color = White50,
                            fontSize = 10.sp,
                            lineHeight = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            Text(
                text = "EXOPLAYER QUEUE",
                color = White70,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(10.dp))

            tracks.forEachIndexed { index, track ->
                val isCurrent = index == selectedTrackIndex
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isCurrent) White15 else PureBlack)
                        .border(1.dp, if (isCurrent) PureWhite else BorderWhite, RoundedCornerShape(12.dp))
                        .clickable {
                            selectedTrackIndex = index
                            progressSeconds = 0f
                            isPlaying = true
                        }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "0${index + 1}",
                        color = if (isCurrent) PureWhite else White50,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = track.title,
                            color = PureWhite,
                            fontSize = 13.sp,
                            fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium
                        )
                        Text(
                            text = track.subtitle,
                            color = White70,
                            fontSize = 11.sp
                        )
                    }
                    if (isCurrent && isPlaying) {
                        Text(
                            text = "PLAYING",
                            color = PureWhite,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
