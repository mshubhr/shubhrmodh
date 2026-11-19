package com.app.shubhrmodh.components.launcher

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.SolidColor
import com.app.shubhrmodh.components.motion.createAnimatedGreenMatrix
import com.app.shubhrmodh.components.motion.hoverScale
import com.app.shubhrmodh.components.motion.pressScale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.shubhrmodh.ai.AiActionTarget
import com.app.shubhrmodh.components.CustomIcons
import com.app.shubhrmodh.components.ai.PortfolioAiModal
import com.app.shubhrmodh.data.ExperienceItem
import com.app.shubhrmodh.data.PortfolioRepository
import com.app.shubhrmodh.data.ProjectItem
import com.app.shubhrmodh.data.SkillCategory
import com.app.shubhrmodh.prefersReducedMotion
import com.app.shubhrmodh.theme.AndroidBorderDark
import com.app.shubhrmodh.theme.AndroidCardDark
import com.app.shubhrmodh.theme.AndroidCardInner
import com.app.shubhrmodh.theme.AndroidDarkBg
import com.app.shubhrmodh.theme.AndroidGreenAccent
import com.app.shubhrmodh.theme.AndroidGreenDarkContainer
import com.app.shubhrmodh.theme.AndroidSoftGrey
import com.app.shubhrmodh.theme.AndroidTextWhite
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import shubhrmodh.app.shared.generated.resources.Res
import shubhrmodh.app.shared.generated.resources.zoro

private enum class PortfolioSection(val index: Int) {
    Hero(0), SelectedWork(1), Experience(2), Skills(3), AboutContact(4)
}

@Composable
fun WebPortfolioScreen(
    onToggleLauncherMode: (() -> Unit)? = null
) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current
    val reduceMotion = remember { prefersReducedMotion() }

    var showCompanyModal by remember { mutableStateOf(false) }
    var showAiModal by remember { mutableStateOf(false) }

    val goTo: (PortfolioSection) -> Unit = { section ->
        scope.launch {
            if (reduceMotion) state.scrollToItem(section.index)
            else state.animateScrollToItem(section.index)
        }
    }
    val projects = PortfolioRepository.projects
    val activeSection =
        PortfolioSection.entries.firstOrNull { it.index == state.firstVisibleItemIndex }
            ?: PortfolioSection.Hero

    Box(
        modifier = Modifier.fillMaxSize().background(AndroidDarkBg)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            PortfolioNav(
                activeSection = activeSection,
                onSelect = goTo,
                onHome = { showCompanyModal = true },
                onOpenAi = { showAiModal = true },
                onToggleLauncherMode = onToggleLauncherMode
            )
            LazyColumn(
                state = state,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 40.dp)
            ) {
                item(key = "hero") {
                    HeroSection(
                        reduceMotion = reduceMotion,
                        onProjects = { goTo(PortfolioSection.SelectedWork) },
                        onContact = { goTo(PortfolioSection.AboutContact) })
                }
                item(key = "projects") {
                    SelectedWorkSection(
                        projects = projects,
                        reduceMotion = reduceMotion,
                        openUri = uriHandler::openUri
                    )
                }
                item(key = "experience") {
                    ExperienceSection()
                }
                item(key = "skills") {
                    TechnicalSkillsSection()
                }
                item(key = "about_contact") {
                    AboutAndContactSection(openUri = uriHandler::openUri)
                }
            }
        }

        // Floating Action Button for AI Assistant
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(AndroidGreenDarkContainer)
                .border(1.dp, AndroidGreenAccent, RoundedCornerShape(24.dp))
                .clickable { showAiModal = true }
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = CustomIcons.Sparkles,
                    contentDescription = null,
                    tint = AndroidGreenAccent,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "Ask AI Assistant ✨",
                    color = AndroidGreenAccent,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (showCompanyModal) {
            CompanyConnectModal(
                openUri = uriHandler::openUri,
                onDismiss = { showCompanyModal = false }
            )
        }

        if (showAiModal) {
            PortfolioAiModal(
                onActionSelected = { target ->
                    when (target) {
                        AiActionTarget.Projects -> goTo(PortfolioSection.SelectedWork)
                        AiActionTarget.Experience -> goTo(PortfolioSection.Experience)
                        AiActionTarget.Skills -> goTo(PortfolioSection.Skills)
                        AiActionTarget.Contact -> goTo(PortfolioSection.AboutContact)
                    }
                },
                onDismiss = { showAiModal = false }
            )
        }
    }
}

@Composable
private fun PortfolioNav(
    activeSection: PortfolioSection,
    onSelect: (PortfolioSection) -> Unit,
    onHome: () -> Unit,
    onOpenAi: () -> Unit,
    onToggleLauncherMode: (() -> Unit)? = null
) {
    BoxWithConstraints(
        Modifier.fillMaxWidth().background(AndroidDarkBg)
    ) {
        val compact = maxWidth < 680.dp
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                horizontal = if (compact) 20.dp else 56.dp, vertical = 18.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val logoInteraction = remember { MutableInteractionSource() }
            val isLogoHovered by logoInteraction.collectIsHoveredAsState()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.clip(RoundedCornerShape(8.dp)).clickable(
                    role = Role.Button,
                    interactionSource = logoInteraction,
                    indication = null,
                    onClick = onHome
                ).padding(horizontal = 4.dp, vertical = 2.dp)
                    .semantics { contentDescription = "Shubhr Modh home" }) {
                Text(
                    text = "SHUBHR MODH",
                    color = if (isLogoHovered) AndroidGreenAccent else AndroidTextWhite,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.3).sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.clip(RoundedCornerShape(20.dp))
                        .background(AndroidGreenDarkContainer).border(
                            1.dp,
                            if (isLogoHovered) AndroidGreenAccent else Color.Transparent,
                            RoundedCornerShape(20.dp)
                        ).padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Box(
                        Modifier.size(6.dp).clip(CircleShape).background(AndroidGreenAccent)
                    )
                    Text(
                        text = "Available for opportunities",
                        color = AndroidGreenAccent,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(if (compact) 12.dp else 24.dp)
            ) {
                val links = listOf(
                    "Work" to PortfolioSection.SelectedWork,
                    "Experience" to PortfolioSection.Experience,
                    "Skills" to PortfolioSection.Skills,
                    "About & Contact" to PortfolioSection.AboutContact
                )
                links.forEach { (label, section) ->
                    val linkInteraction = remember { MutableInteractionSource() }
                    val isLinkHovered by linkInteraction.collectIsHoveredAsState()
                    val isActive = section == activeSection

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = label,
                            modifier = Modifier.clickable(
                                role = Role.Button,
                                interactionSource = linkInteraction,
                                indication = null
                            ) { onSelect(section) }.padding(vertical = 6.dp),
                            color = if (isActive || isLinkHovered) AndroidGreenAccent else AndroidSoftGrey,
                            fontSize = 13.sp,
                            fontWeight = if (isActive || isLinkHovered) FontWeight.SemiBold else FontWeight.Medium
                        )
                        Box(
                            Modifier.width(16.dp).height(2.dp)
                                .background(if (isActive || isLinkHovered) AndroidGreenAccent else Color.Transparent)
                        )
                    }
                }

                val aiInteraction = remember { MutableInteractionSource() }
                val isAiHovered by aiInteraction.collectIsHoveredAsState()

                Row(
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                        .background(if (isAiHovered) AndroidGreenDarkContainer else AndroidCardDark)
                        .border(
                            1.dp,
                            if (isAiHovered) AndroidGreenAccent else AndroidBorderDark,
                            RoundedCornerShape(8.dp)
                        ).clickable(
                            role = Role.Button,
                            interactionSource = aiInteraction,
                            indication = null,
                            onClick = onOpenAi
                        ).padding(horizontal = 12.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = if (compact) "Ask AI" else "Ask AI ✨",
                        color = if (isAiHovered) AndroidGreenAccent else AndroidTextWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = CustomIcons.Sparkles,
                        contentDescription = null,
                        tint = AndroidGreenAccent,
                        modifier = Modifier.size(13.dp)
                    )
                }

                if (onToggleLauncherMode != null) {
                    val simInteraction = remember { MutableInteractionSource() }
                    val isSimHovered by simInteraction.collectIsHoveredAsState()

                    Row(
                        modifier = Modifier.clip(RoundedCornerShape(8.dp))
                            .background(if (isSimHovered) AndroidGreenDarkContainer else AndroidCardDark)
                            .border(
                                1.dp,
                                if (isSimHovered) AndroidGreenAccent else AndroidBorderDark,
                                RoundedCornerShape(8.dp)
                            ).clickable(
                                role = Role.Button,
                                interactionSource = simInteraction,
                                indication = null,
                                onClick = onToggleLauncherMode
                            ).padding(horizontal = 12.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = if (compact) "Simulator" else "Android Simulator",
                            color = if (isSimHovered) AndroidGreenAccent else AndroidTextWhite,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = CustomIcons.Sparkles,
                            contentDescription = null,
                            tint = AndroidGreenAccent,
                            modifier = Modifier.size(13.dp)
                        )
                    }
                }
            }
        }
        Box(
            Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(1.dp)
                .background(AndroidBorderDark)
        )
    }
}

@Composable
private fun CompanyConnectModal(
    openUri: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var companyNameText by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }
    var successMsg by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0x99000000)).clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onDismiss
        ), contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.widthIn(max = 520.dp).fillMaxWidth(0.92f).clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {}
            ).border(1.dp, AndroidBorderDark, RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = AndroidCardDark),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(28.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Company & Recruiter Connect",
                            color = AndroidTextWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Interested in hiring or collaborating? Send your company details directly to Shubhr.",
                            color = AndroidSoftGrey,
                            fontSize = 13.sp
                        )
                    }

                    Text(
                        text = "✕",
                        color = AndroidSoftGrey,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable(
                            role = Role.Button,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onDismiss
                        ).padding(4.dp)
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "COMPANY NAME *",
                            color = AndroidGreenAccent,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        StyledTextInput(
                            value = companyNameText,
                            onValueChange = {
                                companyNameText = it
                                successMsg = ""
                            },
                            placeholder = "e.g. Google, Microsoft, Meta..."
                        )
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "EMAIL / ROLE NOTE (OPTIONAL)",
                            color = AndroidSoftGrey,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        StyledTextInput(
                            value = noteText,
                            onValueChange = {
                                noteText = it
                                successMsg = ""
                            },
                            placeholder = "e.g. recruiter@company.com — Senior Android position"
                        )
                    }

                    if (successMsg.isNotBlank()) {
                        Text(
                            text = successMsg,
                            color = AndroidGreenAccent,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        PrimaryButton(
                            label = "Send Direct Inquiry",
                            onClick = {
                                if (companyNameText.isNotBlank()) {
                                    val subject = "Recruiter Interest: ${companyNameText.trim()}"
                                    val body = "Company: ${companyNameText.trim()}\nDetails: ${noteText.trim()}"
                                    val mailUrl = "mailto:${PortfolioRepository.contact.email}?subject=${subject.replace(" ", "%20")}&body=${body.replace(" ", "%20").replace("\n", "%0A")}"
                                    openUri(mailUrl)
                                    successMsg = "✓ Inquiry prepared! Opening mail client to send directly to Shubhr."
                                    companyNameText = ""
                                    noteText = ""
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StyledTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        cursorBrush = SolidColor(AndroidGreenAccent),
        textStyle = TextStyle(
            color = AndroidTextWhite, fontSize = 14.sp, fontWeight = FontWeight.Medium
        ),
        modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
            .background(AndroidCardInner).border(1.dp, AndroidBorderDark, RoundedCornerShape(8.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        decorationBox = { innerTextField ->
            Box {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder, color = AndroidSoftGrey, fontSize = 14.sp
                    )
                }
                innerTextField()
            }
        })
}

@Composable
private fun HeroSection(
    reduceMotion: Boolean, onProjects: () -> Unit, onContact: () -> Unit
) {
    BoxWithConstraints(
        Modifier.fillMaxWidth().background(AndroidDarkBg)
    ) {
        val compact = maxWidth < 768.dp
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = if (compact) 24.dp else 64.dp)
                .padding(
                    top = if (compact) 40.dp else 64.dp, bottom = if (compact) 48.dp else 72.dp
                )
        ) {
            AnimatedVisibility(
                visible = true,
                enter = if (reduceMotion) EnterTransition.None else fadeIn(tween(400)) + slideInVertically(
                    tween(400)
                ) { it / 16 }) {
                if (compact) {
                    val compactZoroInteraction = remember { MutableInteractionSource() }
                    val isCompactZoroHovered by compactZoroInteraction.collectIsHoveredAsState()
                    val compactZoroProgress by animateFloatAsState(
                        targetValue = if (isCompactZoroHovered) 1f else 0f,
                        animationSpec = tween(durationMillis = 250),
                        label = "CompactZoroProgress"
                    )
                    val compactZoroBorderColor by animateColorAsState(
                        targetValue = if (isCompactZoroHovered) AndroidGreenAccent else AndroidBorderDark,
                        animationSpec = tween(durationMillis = 250),
                        label = "CompactZoroBorder"
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(280.dp)
                                .hoverScale(isCompactZoroHovered, scale = 1.015f, reduceMotion = reduceMotion)
                                .clip(RoundedCornerShape(16.dp)).background(AndroidCardDark)
                                .border(
                                    1.dp,
                                    compactZoroBorderColor,
                                    RoundedCornerShape(16.dp)
                                )
                                .clickable(
                                    interactionSource = compactZoroInteraction,
                                    indication = null,
                                    onClick = {}
                                )
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.zoro),
                                contentDescription = "Zoro Artwork",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                colorFilter = createAnimatedGreenMatrix(compactZoroProgress)
                            )
                        }

                        Text(
                            text = "Hi, I'm Shubhr.",
                            color = AndroidGreenAccent,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = (-0.5).sp
                        )
                        Text(
                            text = "Android Developer building\nthoughtful mobile experiences.",
                            color = AndroidTextWhite,
                            fontSize = 34.sp,
                            lineHeight = 42.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-1.2).sp
                        )
                        Text(
                            text = "Focused on building reliable, high-performance Android applications and shared Kotlin Multiplatform architectures that balance engineering rigor with clean user experience.",
                            color = AndroidSoftGrey,
                            fontSize = 15.sp,
                            lineHeight = 24.sp,
                            modifier = Modifier.fillMaxWidth()
                        )

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            val techStack = listOf(
                                "Kotlin",
                                "Jetpack Compose",
                                "Kotlin Multiplatform",
                                "Navigation 3",
                                "MVVM & Clean Architecture"
                            )
                            techStack.forEach { tech ->
                                Box(
                                    modifier = Modifier.clip(RoundedCornerShape(6.dp))
                                        .background(AndroidCardDark)
                                        .border(1.dp, AndroidBorderDark, RoundedCornerShape(6.dp))
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = tech,
                                        color = AndroidTextWhite,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(8.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PrimaryButton(
                                label = "Explore my work", onClick = onProjects
                            )
                            SecondaryButton(onClick = onContact)
                        }
                    }
                } else {
                    val desktopZoroInteraction = remember { MutableInteractionSource() }
                    val isDesktopZoroHovered by desktopZoroInteraction.collectIsHoveredAsState()
                    val desktopZoroProgress by animateFloatAsState(
                        targetValue = if (isDesktopZoroHovered) 1f else 0f,
                        animationSpec = tween(durationMillis = 250),
                        label = "DesktopZoroProgress"
                    )
                    val desktopZoroBorderColor by animateColorAsState(
                        targetValue = if (isDesktopZoroHovered) AndroidGreenAccent else AndroidBorderDark,
                        animationSpec = tween(durationMillis = 250),
                        label = "DesktopZoroBorder"
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f).padding(end = 40.dp),
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Text(
                                text = "Hi, I'm Shubhr.",
                                color = AndroidGreenAccent,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = (-0.5).sp
                            )
                            Text(
                                text = "Android Developer building\nthoughtful mobile experiences.",
                                color = AndroidTextWhite,
                                fontSize = 54.sp,
                                lineHeight = 62.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = (-1.2).sp
                            )
                            Text(
                                text = "Focused on building reliable, high-performance Android applications and shared Kotlin Multiplatform architectures that balance engineering rigor with clean user experience.",
                                color = AndroidSoftGrey,
                                fontSize = 18.sp,
                                lineHeight = 28.sp,
                                modifier = Modifier.fillMaxWidth()
                            )

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.padding(top = 4.dp)
                            ) {
                                val techStack = listOf(
                                    "Kotlin",
                                    "Jetpack Compose",
                                    "Kotlin Multiplatform",
                                    "Navigation 3",
                                    "MVVM & Clean Architecture"
                                )
                                techStack.forEach { tech ->
                                    Box(
                                        modifier = Modifier.clip(RoundedCornerShape(6.dp))
                                            .background(AndroidCardDark).border(
                                                1.dp, AndroidBorderDark, RoundedCornerShape(6.dp)
                                            ).padding(horizontal = 12.dp, vertical = 6.dp)
                                    ) {
                                        Text(
                                            text = tech,
                                            color = AndroidTextWhite,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }

                            Spacer(Modifier.height(8.dp))

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                PrimaryButton(
                                    label = "Explore my work", onClick = onProjects
                                )
                                SecondaryButton(onClick = onContact)
                            }
                        }

                        Box(
                            modifier = Modifier.size(width = 380.dp, height = 440.dp)
                                .hoverScale(isDesktopZoroHovered, scale = 1.015f, reduceMotion = reduceMotion)
                                .clip(RoundedCornerShape(16.dp)).background(AndroidCardDark)
                                .border(
                                    1.dp,
                                    desktopZoroBorderColor,
                                    RoundedCornerShape(16.dp)
                                )
                                .clickable(
                                    interactionSource = desktopZoroInteraction,
                                    indication = null,
                                    onClick = {}
                                )
                        ) {
                            Image(
                                painter = painterResource(Res.drawable.zoro),
                                contentDescription = "Zoro Artwork",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                colorFilter = createAnimatedGreenMatrix(desktopZoroProgress)
                            )
                        }
                    }
                }
            }
        }
        Box(
            Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(1.dp)
                .background(AndroidBorderDark)
        )
    }
}

@Composable
private fun PrimaryButton(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = CustomIcons.ArrowRight,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val bgColor by animateColorAsState(
        targetValue = if (isHovered) Color(0xFF2ECC71) else AndroidGreenAccent,
        animationSpec = tween(durationMillis = 180),
        label = "PrimaryBtnBg"
    )

    Row(
        modifier = Modifier
            .hoverScale(isHovered, scale = 1.03f)
            .pressScale(interactionSource)
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .clickable(
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ).padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label, color = Color(0xFF0B0F0D), fontSize = 14.sp, fontWeight = FontWeight.Bold
        )
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF0B0F0D),
                modifier = Modifier.size(15.dp)
            )
        }
    }
}

@Composable
private fun SecondaryButton(
    label: String = "Get in touch",
    icon: androidx.compose.ui.graphics.vector.ImageVector? = CustomIcons.ArrowUpRight,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val bgColor by animateColorAsState(
        targetValue = if (isHovered) AndroidGreenDarkContainer else AndroidCardDark,
        animationSpec = tween(durationMillis = 180),
        label = "SecondaryBtnBg"
    )
    val borderColor by animateColorAsState(
        targetValue = if (isHovered) AndroidGreenAccent else AndroidBorderDark,
        animationSpec = tween(durationMillis = 180),
        label = "SecondaryBtnBorder"
    )
    val contentColor by animateColorAsState(
        targetValue = if (isHovered) AndroidGreenAccent else AndroidTextWhite,
        animationSpec = tween(durationMillis = 180),
        label = "SecondaryBtnText"
    )

    Row(
        modifier = Modifier
            .hoverScale(isHovered, scale = 1.03f)
            .pressScale(interactionSource)
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable(
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ).padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            color = contentColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(15.dp)
            )
        }
    }
}

@Composable
private fun SelectedWorkSection(
    projects: List<ProjectItem>, reduceMotion: Boolean, openUri: (String) -> Unit
) {
    SectionContainer(
        eyebrow = "01 — SELECTED WORK", title = "A few things I've built."
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            projects.forEachIndexed { index, project ->
                AnimatedVisibility(
                    visible = true,
                    enter = if (reduceMotion) EnterTransition.None else fadeIn(tween(350)) + slideInVertically(
                        tween(350)
                    ) { it / 20 }) {
                    ProjectCard(
                        project = project, index = index + 1, openUri = openUri
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectCard(
    project: ProjectItem, index: Int, openUri: (String) -> Unit
) {
    val hoverSource = remember { MutableInteractionSource() }
    val isHovered by hoverSource.collectIsHoveredAsState()

    Card(
        modifier = Modifier.fillMaxWidth().border(
            1.dp,
            if (isHovered) AndroidGreenAccent else AndroidBorderDark,
            RoundedCornerShape(12.dp)
        ),
        colors = CardDefaults.cardColors(containerColor = AndroidCardDark),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "0$index",
                        color = AndroidGreenAccent,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "•", color = AndroidSoftGrey, fontSize = 13.sp
                    )
                    Text(
                        text = project.category,
                        color = AndroidSoftGrey,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.5.sp
                    )
                }

                if (project.isPrivate) {
                    Text(
                        text = "ENTERPRISE / PRIVATE",
                        color = AndroidSoftGrey,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.8.sp,
                        modifier = Modifier.background(AndroidCardInner, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                } else if (project.githubUrl.isNotBlank()) {
                    val repoInteraction = remember { MutableInteractionSource() }
                    val isRepoHovered by repoInteraction.collectIsHoveredAsState()

                    Row(
                        modifier = Modifier.clickable(
                            role = Role.Button,
                            interactionSource = repoInteraction,
                            indication = null
                        ) { openUri(project.githubUrl) }.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "View Repository",
                            color = if (isRepoHovered) Color(0xFF2ECC71) else AndroidGreenAccent,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Icon(
                            imageVector = CustomIcons.ArrowUpRight,
                            contentDescription = null,
                            tint = if (isRepoHovered) Color(0xFF2ECC71) else AndroidGreenAccent,
                            modifier = Modifier.size(13.dp)
                        )
                    }
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = project.name,
                    color = AndroidTextWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = project.subtitle,
                    color = AndroidSoftGrey,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = project.description,
                color = AndroidTextWhite,
                fontSize = 15.sp,
                lineHeight = 23.sp
            )

            Column(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
                    .background(AndroidCardInner)
                    .border(1.dp, AndroidBorderDark, RoundedCornerShape(8.dp)).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (project.problem.isNotBlank()) {
                    StructuredField(label = "PROBLEM", content = project.problem)
                }
                if (project.contribution.isNotBlank()) {
                    StructuredField(label = "CONTRIBUTION", content = project.contribution)
                }
                if (project.outcome.isNotBlank()) {
                    StructuredField(
                        label = "OUTCOME", content = project.outcome, isHighlight = true
                    )
                }
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                project.tags.forEach { tag ->
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(4.dp))
                            .background(AndroidGreenDarkContainer)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = tag,
                            color = AndroidGreenAccent,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StructuredField(label: String, content: String, isHighlight: Boolean = false) {
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Text(
            text = label,
            color = if (isHighlight) AndroidGreenAccent else AndroidSoftGrey,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Text(
            text = content, color = AndroidTextWhite, fontSize = 13.sp, lineHeight = 19.sp
        )
    }
}

@Composable
private fun ExperienceSection() {
    val experiences = PortfolioRepository.experiences
    SectionContainer(
        eyebrow = "02 — EXPERIENCE", title = "Where I've worked and what I've contributed."
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            experiences.forEach { exp ->
                ExperienceCard(item = exp)
            }
        }
    }
}

@Composable
private fun ExperienceCard(item: ExperienceItem) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .border(1.dp, AndroidBorderDark, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = AndroidCardDark),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = item.role,
                        color = AndroidTextWhite,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "${item.company}  •  ${item.location}",
                        color = AndroidSoftGrey,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Text(
                    text = item.period,
                    color = AndroidGreenAccent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clip(RoundedCornerShape(6.dp))
                        .background(AndroidGreenDarkContainer)
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                item.bullets.forEach { bullet ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text("•", color = AndroidGreenAccent, fontSize = 14.sp)
                        Text(
                            text = bullet,
                            color = AndroidTextWhite,
                            fontSize = 14.sp,
                            lineHeight = 21.sp
                        )
                    }
                }
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                item.tags.forEach { tag ->
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(4.dp))
                            .background(AndroidCardInner)
                            .border(1.dp, AndroidBorderDark, RoundedCornerShape(4.dp))
                            .padding(horizontal = 9.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = tag,
                            color = AndroidSoftGrey,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TechnicalSkillsSection() {
    val categories = PortfolioRepository.skillCategories
    SectionContainer(
        eyebrow = "03 — TECHNICAL SKILLS", title = "Tools I work with."
    ) {
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            val compact = maxWidth < 680.dp
            if (compact) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    categories.forEach { category ->
                        SkillCategoryCard(category = category)
                    }
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    categories.chunked(2).forEach { pair ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            pair.forEach { category ->
                                SkillCategoryCard(
                                    category = category, modifier = Modifier.weight(1f)
                                )
                            }
                            if (pair.size == 1) {
                                Spacer(Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SkillCategoryCard(
    category: SkillCategory, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
            .border(1.dp, AndroidBorderDark, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = AndroidCardDark),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = category.title,
                color = AndroidGreenAccent,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                category.skills.forEach { skill ->
                    Box(
                        modifier = Modifier.clip(RoundedCornerShape(6.dp))
                            .background(AndroidCardInner)
                            .border(1.dp, AndroidBorderDark, RoundedCornerShape(6.dp))
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = skill,
                            color = AndroidTextWhite,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AboutAndContactSection(openUri: (String) -> Unit) {
    val contact = PortfolioRepository.contact
    val education = PortfolioRepository.education
    val achievement = PortfolioRepository.achievements.first()

    SectionContainer(
        eyebrow = "04 — ABOUT & CONTACT", title = "A little more about me & Get in touch."
    ) {
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            val compact = maxWidth < 768.dp
            if (compact) {
                Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    AboutCard(education, achievement)
                    ContactCard(contact, openUri)
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AboutCard(education, achievement, modifier = Modifier.weight(1f))
                    ContactCard(contact, openUri, modifier = Modifier.weight(1f))
                }
            }
        }

        Spacer(Modifier.height(48.dp))
        Box(
            Modifier.fillMaxWidth().height(1.dp).background(AndroidBorderDark)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SHUBHR MODH  /  ANDROID · KOTLIN · KMP",
                color = AndroidSoftGrey,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "SURAT, GUJARAT, INDIA",
                color = AndroidSoftGrey,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun AboutCard(
    education: com.app.shubhrmodh.data.EducationItem,
    achievement: com.app.shubhrmodh.data.AchievementItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
            .border(1.dp, AndroidBorderDark, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = AndroidCardDark),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "ABOUT & BACKGROUND",
                color = AndroidGreenAccent,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "I'm Shubhr Modh, an Android developer based in Surat, Gujarat. I specialize in building robust production Android applications using Kotlin, Jetpack Compose, and Kotlin Multiplatform.",
                color = AndroidTextWhite,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )

            Box(
                Modifier.fillMaxWidth().height(1.dp).background(AndroidBorderDark)
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "EDUCATION",
                    color = AndroidSoftGrey,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = education.degree,
                    color = AndroidTextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${education.institution}  (${education.period})",
                    color = AndroidSoftGrey,
                    fontSize = 13.sp
                )
                Text(
                    text = "${education.cgpa}  •  ${education.location}",
                    color = AndroidSoftGrey,
                    fontSize = 12.sp
                )
            }

            Box(
                Modifier.fillMaxWidth().height(1.dp).background(AndroidBorderDark)
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "ACHIEVEMENT",
                    color = AndroidSoftGrey,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "${achievement.title} — ${achievement.tag}",
                    color = AndroidTextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = achievement.description,
                    color = AndroidSoftGrey,
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )
            }
        }
    }
}

@Composable
private fun ContactCard(
    contact: com.app.shubhrmodh.data.ContactInfo,
    openUri: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
            .border(1.dp, AndroidBorderDark, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = AndroidCardDark),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "GET IN TOUCH",
                color = AndroidGreenAccent,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Interested in working together or discussing mobile engineering? Feel free to reach out directly via email or connect on social platforms.",
                color = AndroidTextWhite,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )

            val mailInteraction = remember { MutableInteractionSource() }
            val isMailHovered by mailInteraction.collectIsHoveredAsState()

            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
                    .background(AndroidGreenDarkContainer).border(
                        1.dp,
                        if (isMailHovered) AndroidGreenAccent else Color.Transparent,
                        RoundedCornerShape(8.dp)
                    ).clickable(
                        role = Role.Button, interactionSource = mailInteraction, indication = null
                    ) { openUri("mailto:${contact.email}") }.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "EMAIL ADDRESS",
                        color = AndroidGreenAccent,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = contact.email,
                        color = AndroidTextWhite,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Mail",
                        color = AndroidGreenAccent,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = CustomIcons.ArrowUpRight,
                        contentDescription = null,
                        tint = AndroidGreenAccent,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Text(
                text = "PROFILES & LINKS",
                color = AndroidSoftGrey,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )

            val links = listOf(
                "GitHub" to contact.githubUrl,
                "LinkedIn" to contact.linkedinUrl,
                "Google Dev" to contact.googleDeveloperUrl,
                "Twitter / X" to contact.twitterUrl,
                "GitLab" to contact.gitlabUrl,
                "Medium" to contact.mediumUrl
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                links.forEach { (label, url) ->
                    val linkPillInteraction = remember { MutableInteractionSource() }
                    val isPillHovered by linkPillInteraction.collectIsHoveredAsState()

                    Row(
                        modifier = Modifier.clip(RoundedCornerShape(6.dp))
                            .background(if (isPillHovered) AndroidGreenDarkContainer else AndroidCardInner)
                            .border(
                                1.dp,
                                if (isPillHovered) AndroidGreenAccent else AndroidBorderDark,
                                RoundedCornerShape(6.dp)
                            ).clickable(
                                role = Role.Button,
                                interactionSource = linkPillInteraction,
                                indication = null
                            ) { openUri(url) }.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = label,
                            color = if (isPillHovered) AndroidGreenAccent else AndroidTextWhite,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Icon(
                            imageVector = CustomIcons.ArrowUpRight,
                            contentDescription = null,
                            tint = AndroidGreenAccent,
                            modifier = Modifier.size(13.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionContainer(
    eyebrow: String, title: String, content: @Composable ColumnScope.() -> Unit
) {
    BoxWithConstraints(
        Modifier.fillMaxWidth().background(AndroidDarkBg)
    ) {
        val compact = maxWidth < 768.dp
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = if (compact) 24.dp else 64.dp)
                .padding(
                    top = if (compact) 48.dp else 64.dp, bottom = if (compact) 48.dp else 64.dp
                )
        ) {
            Text(
                text = eyebrow,
                color = AndroidGreenAccent,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = title,
                color = AndroidTextWhite,
                fontSize = if (compact) 26.sp else 36.sp,
                lineHeight = if (compact) 32.sp else 44.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp
            )
            Spacer(Modifier.height(28.dp))
            content()
        }
    }
}

@Preview(name = "Desktop portfolio", widthDp = 1440, heightDp = 1000)
@Composable
private fun WebPortfolioPreview() {
    WebPortfolioScreen()
}
