package com.app.shubhrmodh.components.launcher

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.components.CustomIcons
import com.app.shubhrmodh.data.PortfolioRepository
import kotlinx.coroutines.launch
import com.app.shubhrmodh.navigation.NavKey
import com.app.shubhrmodh.theme.BorderWhite
import com.app.shubhrmodh.theme.BorderWhiteBold
import com.app.shubhrmodh.theme.PortfolioTheme
import com.app.shubhrmodh.theme.PureBlack
import com.app.shubhrmodh.theme.PureWhite
import com.app.shubhrmodh.theme.White08
import com.app.shubhrmodh.theme.White15
import com.app.shubhrmodh.theme.White50
import com.app.shubhrmodh.theme.White70

@Composable
fun AndroidHomeScreen(
    onNavigate: (NavKey) -> Unit, modifier: Modifier = Modifier
) {
    val contact = PortfolioRepository.contact
    val uriHandler = LocalUriHandler.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize().background(PureBlack)
            .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth().verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Monday, Nov 9",
                    color = White70,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "${contact.name} • ${contact.title}",
                        color = PureWhite,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Surat, Gujarat • 2+ Yrs Exp • Android SDK 35 & 16KB Page-Size",
                    color = White50,
                    fontSize = 11.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().height(48.dp).clip(RoundedCornerShape(24.dp))
                    .background(White08).border(1.dp, BorderWhite, RoundedCornerShape(24.dp))
                    .clickable { onNavigate(NavKey.Projects) }.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = CustomIcons.Search,
                    contentDescription = "Search",
                    tint = PureWhite,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "Search projects, skills & tech stack...",
                    color = White50,
                    fontSize = 13.sp,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            val appList = listOf(
                LauncherAppItem("About", CustomIcons.School) { onNavigate(NavKey.About) },
                LauncherAppItem("Experience", CustomIcons.Work) { onNavigate(NavKey.Experience) },
                LauncherAppItem("Projects", CustomIcons.Code) { onNavigate(NavKey.Projects) },
                LauncherAppItem("Skills", CustomIcons.Sparkles) { onNavigate(NavKey.Skills) },
                LauncherAppItem("ExoPlayer", CustomIcons.Disc) { onNavigate(NavKey.ExoPlayer) },
                LauncherAppItem("Honors", CustomIcons.Trophy) { onNavigate(NavKey.Achievements) },
                LauncherAppItem("Dialer", CustomIcons.Phone) { onNavigate(NavKey.Contact) },
                LauncherAppItem(
                    "GitHub", CustomIcons.GitHub
                ) { uriHandler.openUri(contact.githubUrl) },
                LauncherAppItem(
                    "LinkedIn", CustomIcons.LinkedIn
                ) { uriHandler.openUri(contact.linkedinUrl) },
                LauncherAppItem(
                    "Email", CustomIcons.Mail
                ) { uriHandler.openUri("mailto:${contact.email}") })

            appList.chunked(4).forEach { rowApps ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    rowApps.forEach { app ->
                        AndroidAppIcon(app = app)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            MiniExoPlayerWidget(
                onOpenFullPlayer = { onNavigate(NavKey.ExoPlayer) })

            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.clip(RoundedCornerShape(32.dp)).background(White08)
                .border(1.dp, BorderWhite, RoundedCornerShape(32.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DockItem(
                icon = CustomIcons.Sparkles, label = "Home", onClick = { onNavigate(NavKey.Home) })

            Box(
                modifier = Modifier.width(1.dp).height(24.dp).background(BorderWhite)
            )

            DockItem(
                icon = CustomIcons.Code,
                label = "Projects",
                onClick = { onNavigate(NavKey.Projects) })
            DockItem(
                icon = CustomIcons.Mail,
                label = "Email",
                onClick = { uriHandler.openUri("mailto:${contact.email}") })
            DockItem(
                icon = CustomIcons.LinkedIn,
                label = "LinkedIn",
                onClick = { uriHandler.openUri(contact.linkedinUrl) })
            DockItem(
                icon = CustomIcons.GitHub,
                label = "GitHub",
                onClick = { uriHandler.openUri(contact.githubUrl) })

            Box(
                modifier = Modifier.width(1.dp).height(24.dp).background(BorderWhite)
            )

            DockTopButton {
                coroutineScope.launch {
                    scrollState.animateScrollTo(0)
                }
            }
        }
    }
}

@Composable
fun DockItem(
    icon: ImageVector, label: String, isSelected: Boolean = false, onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val showText = isHovered || isSelected

    Row(
        modifier = Modifier.height(42.dp).graphicsLayer {
            val scale = if (isHovered) 1.08f else 1f
            scaleX = scale
            scaleY = scale
        }.clip(RoundedCornerShape(21.dp)).background(if (showText) White15 else Color.Transparent)
            .border(
                width = if (showText) 1.dp else 0.dp,
                color = if (showText) BorderWhite else Color.Transparent,
                shape = RoundedCornerShape(21.dp)
            ).clickable(
                interactionSource = interactionSource, indication = null, onClick = onClick
            ).padding(horizontal = if (showText) 14.dp else 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier.size(42.dp), contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = PureWhite,
                modifier = Modifier.size(20.dp)
            )
        }

        AnimatedVisibility(visible = showText) {
            Text(
                text = label,
                color = PureWhite,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }
    }
}

@Composable
fun DockTopButton(
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(
        modifier = Modifier.height(42.dp).graphicsLayer {
            val scale = if (isHovered) 1.10f else 1f
            scaleX = scale
            scaleY = scale
        }.clip(RoundedCornerShape(21.dp)).background(if (isHovered) White15 else Color.Transparent)
            .clickable(
                interactionSource = interactionSource, indication = null, onClick = onClick
            ).padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = CustomIcons.ArrowUp,
            contentDescription = "Top",
            tint = PureWhite,
            modifier = Modifier.size(16.dp)
        )
    }
}

data class LauncherAppItem(
    val name: String, val icon: ImageVector, val onClick: () -> Unit
)

@Composable
fun AndroidAppIcon(app: LauncherAppItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(68.dp).clickable(onClick = app.onClick).padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier.size(54.dp).clip(RoundedCornerShape(16.dp)).background(White15)
                .border(1.dp, BorderWhite, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = app.icon,
                contentDescription = app.name,
                tint = PureWhite,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = app.name,
            color = PureWhite,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}


@Composable
fun MiniExoPlayerWidget(
    onOpenFullPlayer: () -> Unit
) {
    var isPlaying by remember { mutableStateOf(true) }

    val infiniteTransition = rememberInfiniteTransition(label = "MiniEqualizer")
    val bar1 by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(400, easing = LinearEasing), RepeatMode.Reverse),
        label = "Bar1"
    )
    val bar2 by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(tween(350, easing = LinearEasing), RepeatMode.Reverse),
        label = "Bar2"
    )
    val bar3 by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(tween(450, easing = LinearEasing), RepeatMode.Reverse),
        label = "Bar3"
    )

    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(White08)
            .border(1.dp, BorderWhiteBold, RoundedCornerShape(16.dp))
            .clickable(onClick = onOpenFullPlayer).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.size(42.dp).clip(CircleShape).background(PureWhite),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = CustomIcons.Disc,
                contentDescription = null,
                tint = PureBlack,
                modifier = Modifier.size(24.dp)
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "ExoPlayer Media3 Engine",
                color = PureWhite,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "ShopFlow Demo • 44.1 kHz Stereo", color = White70, fontSize = 11.sp
            )
        }

        if (isPlaying) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.height(18.dp)
            ) {
                Box(modifier = Modifier.width(3.dp).height((18 * bar1).dp).background(PureWhite))
                Box(modifier = Modifier.width(3.dp).height((18 * bar2).dp).background(PureWhite))
                Box(modifier = Modifier.width(3.dp).height((18 * bar3).dp).background(PureWhite))
            }
        }

        Box(
            modifier = Modifier.size(32.dp).clip(CircleShape).background(PureWhite)
                .clickable { isPlaying = !isPlaying }, contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isPlaying) CustomIcons.Pause else CustomIcons.Play,
                contentDescription = "Play/Pause",
                tint = PureBlack,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun AndroidHomeScreenPreview() {
    PortfolioTheme {
        AndroidHomeScreen(
            onNavigate = {})
    }
}
