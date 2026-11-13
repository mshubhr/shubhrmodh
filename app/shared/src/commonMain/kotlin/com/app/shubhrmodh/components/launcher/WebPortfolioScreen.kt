package com.app.shubhrmodh.components.launcher

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalUriHandler
import com.app.shubhrmodh.prefersReducedMotion
import com.app.shubhrmodh.data.PortfolioRepository
import kotlinx.coroutines.launch

private val Void = Color(0xFF090C0B)
private val Surface = Color(0xFF101512)
private val Forest = Color(0xFF244B3B)
private val Emerald = Color(0xFF6F9B7E)
private val Paper = Color(0xFFE9E8DF)
private val Soft = Color(0xFFA9AEA7)
private val Rule = Color(0xFF303833)
private val Vermilion = Color(0xFF9E493F)

private enum class PortfolioSection(val index: Int) {
    Home(0), Projects(1), About(2), Skills(3), Experience(4), Contact(5)
}

@Composable
fun WebPortfolioScreen() {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current
    val reduceMotion = remember { prefersReducedMotion() }
    val goTo: (PortfolioSection) -> Unit = { section ->
        scope.launch {
            if (reduceMotion) state.scrollToItem(section.index)
            else state.animateScrollToItem(section.index)
        }
    }
    val projects = listOf(
        FeaturedProject(
            name = "FieldSync",
            category = "KOTLIN MULTIPLATFORM  /  FIELD OPERATIONS",
            description = "A cross-platform field operations application built around shared networking and synchronization.",
            highlights = listOf(
                "Shared KMP networking layer with a Ktor sync engine",
                "85%+ code reuse across Android and iOS",
                "OTP authentication, offline caching and device registration"
            ),
            stack = listOf("Kotlin Multiplatform", "Ktor", "Coroutines", "Firebase"),
            url = PortfolioRepository.projects.first { it.name == "FieldSync" }.githubUrl,
            number = "01"
        ),
        FeaturedProject(
            name = "ShopFlow",
            category = "ANDROID  /  COMMERCE",
            description = "An Android shopping application with a resilient, offline-first product and cart experience.",
            highlights = listOf(
                "Product catalog with sorting and pagination",
                "Persistent shopping cart backed by Room",
                "Ktor REST backend with Coroutines and Flow"
            ),
            stack = listOf("Kotlin", "Jetpack Compose", "Room", "Ktor", "Flow"),
            url = null,
            number = "02"
        ),
        FeaturedProject(
            name = "VideoApp",
            category = "VIDEO  /  GO BACKEND",
            description = "A video application backed by a Go service and PostgreSQL.",
            highlights = emptyList(),
            stack = listOf("Go", "PostgreSQL"),
            url = null,
            number = "03"
        )
    )

    Column(Modifier.fillMaxSize().background(Void)) {
        PortfolioNav(onSelect = goTo, onHome = { goTo(PortfolioSection.Home) })
        LazyColumn(
            state = state,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item(key = "home") {
                HeroSection(
                    scrollState = state,
                    reduceMotion = reduceMotion,
                    onProjects = { goTo(PortfolioSection.Projects) },
                    onContact = { goTo(PortfolioSection.Contact) }
                )
            }
            item(key = "projects") { ProjectsSection(projects, reduceMotion, uriHandler::openUri) }
            item(key = "about") { AboutSection(onContact = { goTo(PortfolioSection.Contact) }) }
            item(key = "skills") { SkillsSection() }
            item(key = "experience") { ExperienceSection() }
            item(key = "contact") { ContactSection(onOpen = uriHandler::openUri) }
        }
    }
}

@Composable
private fun PortfolioNav(onSelect: (PortfolioSection) -> Unit, onHome: () -> Unit) {
    BoxWithConstraints(Modifier.fillMaxWidth().background(Void)) {
        val compact = maxWidth < 600.dp
        Row(
            Modifier.fillMaxWidth()
                .padding(horizontal = if (compact) 18.dp else 48.dp, vertical = 17.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.clickable(onClick = onHome)
                    .semantics { contentDescription = "Shubhr Modh, home" },
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text("SM  /  MODH", color = Paper, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.3.sp)
                Text("ANDROID  ·  KOTLIN", color = Soft, fontSize = 9.sp, letterSpacing = 1.1.sp)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(if (compact) 10.dp else 26.dp)) {
                val links = if (compact) {
                    listOf("Work" to PortfolioSection.Projects, "About" to PortfolioSection.About, "Contact" to PortfolioSection.Contact)
                } else {
                    listOf(
                        "Work" to PortfolioSection.Projects,
                        "About" to PortfolioSection.About,
                        "Skills" to PortfolioSection.Skills,
                        "Experience" to PortfolioSection.Experience,
                        "Contact" to PortfolioSection.Contact
                    )
                }
                links.forEach { (label, section) ->
                    Text(
                        text = label,
                        modifier = Modifier.clickable(
                            role = Role.Button,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onSelect(section) }.padding(vertical = 8.dp),
                        color = if (section == PortfolioSection.Contact) Emerald else Soft,
                        fontSize = if (compact) 10.sp else 12.sp,
                        maxLines = 1
                    )
                }
            }
        }
        Box(
            Modifier.align(Alignment.BottomCenter).fillMaxWidth().height(1.dp).background(Rule)
        )
    }
}

@Composable
private fun HeroSection(scrollState: LazyListState, reduceMotion: Boolean, onProjects: () -> Unit, onContact: () -> Unit) {
    BoxWithConstraints(Modifier.fillMaxWidth().background(Void)) {
        val narrow = maxWidth < 760.dp
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = if (maxWidth < 640.dp) 24.dp else 64.dp)
                .height(if (narrow) 820.dp else 650.dp)
                .padding(vertical = 42.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier.weight(if (narrow) 1f else 1.15f),
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = true,
                    enter = if (reduceMotion) EnterTransition.None else fadeIn(tween(500)) + slideInVertically(tween(500)) { it / 20 }
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Box(Modifier.size(7.dp).clip(CircleShape).background(Emerald))
                            Text("ENGINEERING IN KOTLIN  ·  SURAT, INDIA", color = Emerald, fontSize = 10.sp, letterSpacing = 1.5.sp)
                        }
                        Spacer(Modifier.height(28.dp))
                        Text(
                            "SHUBHR\nMODH.",
                            color = Paper,
                            fontSize = if (narrow) 56.sp else 82.sp,
                            lineHeight = if (narrow) 59.sp else 84.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = (-2).sp
                        )
                        Spacer(Modifier.height(19.dp))
                        Text(
                            "ANDROID  /  KOTLIN MULTIPLATFORM DEVELOPER",
                            color = Soft,
                            fontSize = if (narrow) 10.sp else 12.sp,
                            letterSpacing = 1.4.sp
                        )
                        Spacer(Modifier.height(20.dp))
                        Text(
                            "I build software with discipline,\nclarity, and a bias for the useful.",
                            color = Paper,
                            fontSize = if (narrow) 21.sp else 27.sp,
                            lineHeight = if (narrow) 30.sp else 36.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(Modifier.height(14.dp))
                        Text(
                            "From native Android products to shared Kotlin systems, I care about the details that make an app dependable.",
                            color = Soft,
                            fontSize = 14.sp,
                            lineHeight = 23.sp
                        )
                        Spacer(Modifier.height(30.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            ActionLink("Explore my work", primary = true, onClick = onProjects)
                            ActionLink("Contact me", primary = false, onClick = onContact)
                        }
                    }
                }
            }
            if (!narrow) {
                Box(Modifier.weight(0.85f).fillMaxSize(), contentAlignment = Alignment.Center) {
                    SwordStudy(Modifier.fillMaxSize().graphicsLayer {
                        translationY = if (reduceMotion) 0f else scrollState.firstVisibleItemScrollOffset * .035f
                    })
                }
            }
        }
        if (narrow) {
            Box(
                Modifier.fillMaxWidth().height(165.dp).align(Alignment.BottomCenter).padding(bottom = 46.dp),
                contentAlignment = Alignment.Center
            ) { SwordStudy(Modifier.fillMaxSize()) }
        }
        Row(
            Modifier.align(Alignment.BottomStart).clickable(role = Role.Button, onClick = onProjects).padding(bottom = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(Modifier.width(34.dp).height(1.dp).background(Emerald))
            Text("SCROLL TO EXPLORE", color = Soft, fontSize = 9.sp, letterSpacing = 1.4.sp)
        }
    }
}

@Composable
private fun SwordStudy(modifier: Modifier = Modifier) {
    Box(modifier.semantics { contentDescription = "Abstract three sword line illustration" }) {
        Canvas(Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Forest.copy(alpha = 0.34f), Void.copy(alpha = 0f)),
                    center = Offset(w * .57f, h * .45f),
                    radius = w * .54f
                ),
                radius = w * .54f,
                center = Offset(w * .57f, h * .45f)
            )
            val lines = listOf(
                Triple(Offset(w * .19f, h * .83f), Offset(w * .77f, h * .18f), Emerald.copy(alpha = .72f)),
                Triple(Offset(w * .31f, h * .91f), Offset(w * .81f, h * .29f), Paper.copy(alpha = .62f)),
                Triple(Offset(w * .39f, h * .86f), Offset(w * .91f, h * .42f), Vermilion.copy(alpha = .76f))
            )
            lines.forEachIndexed { index, line ->
                drawLine(line.third.copy(alpha = .1f), line.first, line.second, strokeWidth = 11f)
                drawLine(line.third, line.first, line.second, strokeWidth = if (index == 1) 1.6f else 2.2f, cap = StrokeCap.Round)
                val handleEnd = Offset(line.first.x - 22f, line.first.y + 24f)
                drawLine(Soft.copy(alpha = .68f), handleEnd, line.first, strokeWidth = 5f, cap = StrokeCap.Round)
                for (mark in 1..3) {
                    val fraction = mark / 4f
                    val point = Offset(
                        line.first.x + (line.second.x - line.first.x) * fraction,
                        line.first.y + (line.second.y - line.first.y) * fraction
                    )
                    drawCircle(line.third.copy(alpha = .5f), radius = 1.5f, center = point)
                }
            }
            drawCircle(Rule.copy(alpha = .7f), w * .37f, Offset(w * .58f, h * .48f), style = Stroke(1f))
            drawCircle(Forest.copy(alpha = .7f), w * .28f, Offset(w * .58f, h * .48f), style = Stroke(1f))
            drawLine(Rule, Offset(w * .58f, h * .08f), Offset(w * .58f, h * .13f), 1f)
            drawLine(Rule, Offset(w * .58f, h * .83f), Offset(w * .58f, h * .88f), 1f)
        }
        Text(
            "三刀流",
            Modifier.align(Alignment.TopEnd).padding(top = 22.dp, end = 14.dp),
            color = Soft.copy(alpha = .6f),
            fontSize = 15.sp,
            letterSpacing = 4.sp
        )
        Text(
            "DISCIPLINE  /  PRECISION  /  CRAFT",
            Modifier.align(Alignment.BottomEnd).padding(bottom = 15.dp),
            color = Soft.copy(alpha = .7f),
            fontSize = 8.sp,
            letterSpacing = 1.5.sp
        )
    }
}

@Composable
private fun ActionLink(label: String, primary: Boolean, onClick: () -> Unit) {
    val shape = androidx.compose.foundation.shape.RoundedCornerShape(2.dp)
    Row(
        Modifier.clip(shape)
            .background(if (primary) Forest else Color.Transparent)
            .border(1.dp, if (primary) Forest else Rule, shape)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(label, color = Paper, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        Text(if (primary) "↗" else "→", color = if (primary) Emerald else Soft, fontSize = 14.sp)
    }
}

private data class FeaturedProject(
    val name: String,
    val category: String,
    val description: String,
    val highlights: List<String>,
    val stack: List<String>,
    val url: String?,
    val number: String
)

@Composable
private fun ProjectsSection(
    projects: List<FeaturedProject>,
    reduceMotion: Boolean,
    openUri: (String) -> Unit
) {
    SectionFrame("SELECTED WORK", "Built for the real world.", "01  /  PROJECTS") {
        projects.forEachIndexed { index, project ->
            AnimatedVisibility(
                visible = true,
                enter = if (reduceMotion) EnterTransition.None else fadeIn(tween(350)) + slideInVertically(tween(350)) { it / 24 }
            ) {
                ProjectShowcase(
                    project = project,
                    index = index,
                    reduceMotion = reduceMotion,
                    openUri = openUri
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        Text(
            "MORE ON GITHUB  ↗",
            Modifier.clickable(role = Role.Button) { openUri(PortfolioRepository.contact.githubUrl) }
                .padding(vertical = 8.dp),
            color = Emerald,
            fontSize = 10.sp,
            letterSpacing = 1.2.sp
        )
    }
}

@Composable
private fun ProjectShowcase(
    project: FeaturedProject,
    index: Int,
    reduceMotion: Boolean,
    openUri: (String) -> Unit
) {
    var expanded by remember(project.name) { mutableStateOf(false) }
    val hoverSource = remember { MutableInteractionSource() }
    val isHovered by hoverSource.collectIsHoveredAsState()

    Column(Modifier.fillMaxWidth().padding(vertical = 23.dp)) {
        Box(Modifier.fillMaxWidth().height(1.dp).background(Rule))
        BoxWithConstraints(Modifier.fillMaxWidth().padding(top = 24.dp)) {
            val compact = maxWidth < 760.dp
            if (compact) {
                Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
                    ProjectTechnicalStudy(
                        project.name,
                        interactionSource = hoverSource,
                        onOpen = if (project.highlights.isNotEmpty()) ({ expanded = !expanded }) else null
                    )
                    ProjectSummary(project, index, isHovered, hoverSource, expanded, openUri = openUri) {
                        expanded = !expanded
                    }
                }
            } else {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(42.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (index % 2 == 0) {
                        ProjectTechnicalStudy(
                            project.name,
                            Modifier.weight(1.08f),
                            interactionSource = hoverSource,
                            onOpen = if (project.highlights.isNotEmpty()) ({ expanded = !expanded }) else null
                        )
                        ProjectSummary(
                            project, index, isHovered, hoverSource, expanded,
                            openUri = openUri,
                            modifier = Modifier.weight(.92f)
                        ) { expanded = !expanded }
                    } else {
                        ProjectSummary(
                            project, index, isHovered, hoverSource, expanded,
                            openUri = openUri,
                            modifier = Modifier.weight(.92f)
                        ) { expanded = !expanded }
                        ProjectTechnicalStudy(
                            project.name,
                            Modifier.weight(1.08f),
                            interactionSource = hoverSource,
                            onOpen = if (project.highlights.isNotEmpty()) ({ expanded = !expanded }) else null
                        )
                    }
                }
            }
        }
        if (expanded && project.highlights.isNotEmpty()) {
            androidx.compose.animation.AnimatedVisibility(
                visible = true,
                enter = if (reduceMotion) EnterTransition.None else fadeIn(tween(220))
            ) {
                Column(
                    Modifier.fillMaxWidth().padding(top = 20.dp, start = 38.dp, end = 12.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("ENGINEERING CONTRIBUTIONS", color = Emerald, fontSize = 9.sp, letterSpacing = 1.4.sp)
                    project.highlights.forEach { HighlightLine(it) }
                    project.url?.let { url ->
                        Text(
                            "VIEW REPOSITORY  ↗",
                            Modifier.clickable(role = Role.Button) { openUri(url) }.padding(vertical = 8.dp),
                            color = Paper,
                            fontSize = 10.sp,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProjectSummary(
    project: FeaturedProject,
    index: Int,
    hovered: Boolean,
    interactionSource: MutableInteractionSource,
    expanded: Boolean,
    openUri: (String) -> Unit,
    modifier: Modifier = Modifier,
    onToggle: () -> Unit
) {
    Column(modifier.fillMaxWidth()) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("0${index + 1}  /  ${project.category}", color = Emerald, fontSize = 9.sp, letterSpacing = 1.sp)
            project.url?.let { url ->
                Text(
                    "GITHUB ↗",
                    Modifier.clickable(role = Role.Button) { openUri(url) }
                        .padding(vertical = 6.dp),
                    color = Soft,
                    fontSize = 9.sp
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        Text(project.name, color = Paper, fontSize = 31.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(10.dp))
        Text(project.description, color = Soft, fontSize = 14.sp, lineHeight = 23.sp)
        Spacer(Modifier.height(16.dp))
        Text(
            project.stack.joinToString("   /   "),
            color = Paper,
            fontSize = 10.sp,
            lineHeight = 18.sp
        )
        if (project.highlights.isNotEmpty()) {
            Spacer(Modifier.height(18.dp))
            Row(
                Modifier.clickable(
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onToggle
                ).border(1.dp, if (hovered) Emerald else Rule)
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    if (expanded) "Close project notes" else "Read project notes",
                    color = Paper,
                    fontSize = 11.sp
                )
                Text(if (expanded) "−" else "↗", color = Emerald, fontSize = 13.sp)
            }
        }
    }
}

@Composable
private fun HighlightLine(text: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
        Text("—", color = Emerald, fontSize = 12.sp)
        Text(text, color = Soft, fontSize = 13.sp, lineHeight = 20.sp)
    }
}

@Composable
private fun AboutSection(onContact: () -> Unit) {
    SectionFrame("THE APPROACH", "Useful software, built with intent.", "02  /  ABOUT") {
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            val split = maxWidth >= 760.dp
            if (split) {
                Row(horizontalArrangement = Arrangement.spacedBy(55.dp), verticalAlignment = Alignment.Top) {
                    Text("Engineering is a practice of\nshowing up and getting\nthe details right.", Modifier.weight(1f), color = Paper, fontSize = 27.sp, lineHeight = 36.sp, fontWeight = FontWeight.Medium)
                    AboutCopy(Modifier.weight(1f), onContact)
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
                    Text("Engineering is a practice of\nshowing up and getting\nthe details right.", color = Paper, fontSize = 25.sp, lineHeight = 33.sp, fontWeight = FontWeight.Medium)
                    AboutCopy(Modifier.fillMaxWidth(), onContact)
                }
            }
        }
    }
}

@Composable
private fun AboutCopy(modifier: Modifier, onContact: () -> Unit) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(15.dp)) {
        Text(
            "I’m Shubhr, an Android developer from Surat with a Computer Engineering background. I’ve worked on production mobile apps, from real-time communication and location experiences to media playback and commerce.",
            color = Soft, fontSize = 14.sp, lineHeight = 24.sp
        )
        Text(
            "My focus is Kotlin, Jetpack Compose, and clean application architecture. Kotlin Multiplatform is where I’m taking that experience next: sharing the right foundations while keeping each platform’s experience native.",
            color = Soft, fontSize = 14.sp, lineHeight = 24.sp
        )
        Text("LET’S TALK  →", Modifier.clickable(role = Role.Button, onClick = onContact).padding(vertical = 6.dp), color = Emerald, fontSize = 10.sp, letterSpacing = 1.3.sp)
    }
}

@Composable
private fun SkillsSection() {
    val skills = listOf(
        "LANGUAGES" to listOf("Kotlin", "Java"),
        "ANDROID" to listOf("Jetpack Compose", "Android Jetpack", "Room", "Hilt"),
        "MULTIPLATFORM" to listOf("Kotlin Multiplatform", "Coroutines", "Flow", "SQLDelight"),
        "NETWORK & DATA" to listOf("Ktor", "Retrofit", "REST APIs"),
        "WORKFLOW" to listOf("Git", "GitHub")
    )
    SectionFrame("TOOLS & TECHNIQUE", "A toolkit for shipping.", "03  /  SKILLS") {
        Column {
            skills.forEach { (category, values) ->
                Row(
                    Modifier.fillMaxWidth().padding(vertical = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(category, Modifier.width(138.dp), color = Emerald, fontSize = 9.sp, letterSpacing = 1.2.sp)
                    Text(values.joinToString("     /     "), color = Paper, fontSize = 14.sp, lineHeight = 23.sp)
                }
                Box(Modifier.fillMaxWidth().height(1.dp).background(Rule))
            }
        }
    }
}

@Composable
private fun ExperienceSection() {
    val data = PortfolioRepository
    SectionFrame("FIELD NOTES", "Experience earned in production.", "04  /  EXPERIENCE") {
        data.experiences.forEachIndexed { index, item ->
            Row(
                Modifier.fillMaxWidth().padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column(Modifier.width(116.dp)) {
                    Text(item.period.uppercase(), color = Emerald, fontSize = 10.sp, lineHeight = 15.sp)
                    Spacer(Modifier.height(5.dp))
                    Text(item.location, color = Soft, fontSize = 11.sp)
                }
                Column(Modifier.weight(1f)) {
                    Text(item.role, color = Paper, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(4.dp))
                    Text(item.company, color = Soft, fontSize = 13.sp)
                    Spacer(Modifier.height(14.dp))
                    item.bullets.take(3).forEach { HighlightLine(it) }
                }
            }
            if (index < data.experiences.lastIndex) Box(Modifier.fillMaxWidth().height(1.dp).background(Rule))
        }
        Spacer(Modifier.height(18.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.Top) {
            Text("2020 — 2024", Modifier.width(116.dp), color = Emerald, fontSize = 10.sp)
            Column {
                Text(data.education.institution, color = Paper, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(5.dp))
                Text(data.education.degree, color = Soft, fontSize = 12.sp, lineHeight = 19.sp)
                Text("${data.education.location}   /   ${data.education.cgpa}", color = Soft, fontSize = 11.sp)
            }
        }
        Spacer(Modifier.height(24.dp))
        val award = data.achievements.first()
        Row(
            Modifier.fillMaxWidth().background(Surface).padding(18.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text("★", color = Vermilion, fontSize = 14.sp)
            Column {
                Text(award.title + "  /  " + award.tag, color = Paper, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(5.dp))
                Text(award.description, color = Soft, fontSize = 12.sp, lineHeight = 19.sp)
            }
        }
    }
}

@Composable
private fun ContactSection(onOpen: (String) -> Unit) {
    val contact = PortfolioRepository.contact
    SectionFrame("OPEN CHANNEL", "The next good thing starts with a conversation.", "05  /  CONTACT") {
        Text("Have a product to build?", color = Paper, fontSize = 29.sp, lineHeight = 36.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(12.dp))
        Text("I’d be glad to hear what you’re working on.", color = Soft, fontSize = 14.sp)
        Spacer(Modifier.height(22.dp))
        Text(
            contact.email,
            Modifier.clickable(role = Role.Button) { onOpen("mailto:${contact.email}") }.padding(vertical = 5.dp),
            color = Emerald, fontSize = 18.sp
        )
        Spacer(Modifier.height(22.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            listOf(
                "GITHUB" to contact.githubUrl,
                "LINKEDIN" to contact.linkedinUrl,
                "GITLAB" to contact.gitlabUrl,
                "MEDIUM" to contact.mediumUrl
            ).forEach { (label, url) ->
                Text(
                    label,
                    Modifier.clickable(role = Role.Button) { onOpen(url) }.padding(vertical = 8.dp),
                    color = Paper, fontSize = 10.sp, letterSpacing = 1.sp
                )
            }
        }
        Spacer(Modifier.height(48.dp))
        Box(Modifier.fillMaxWidth().height(1.dp).background(Rule))
        Row(Modifier.fillMaxWidth().padding(top = 15.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("SHUBHR MODH  /  ANDROID · KOTLIN · KMP", color = Soft, fontSize = 9.sp, letterSpacing = .8.sp)
            Text("SURAT, INDIA", color = Soft, fontSize = 9.sp, letterSpacing = .8.sp)
        }
    }
}

@Composable
private fun SectionFrame(
    eyebrow: String,
    title: String,
    index: String,
    content: @Composable ColumnScope.() -> Unit
) {
    BoxWithConstraints(Modifier.fillMaxWidth().background(if (index.startsWith("01") || index.startsWith("04")) Surface else Void)) {
        val compact = maxWidth < 640.dp
        Column(
            Modifier.fillMaxWidth().padding(horizontal = if (compact) 24.dp else 64.dp)
                .padding(top = if (compact) 56.dp else 76.dp, bottom = if (compact) 55.dp else 72.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(eyebrow, color = Emerald, fontSize = 9.sp, letterSpacing = 1.5.sp)
                Text(index, color = Soft, fontSize = 9.sp, letterSpacing = 1.sp)
            }
            Spacer(Modifier.height(16.dp))
            Text(
                title,
                color = Paper,
                fontSize = if (compact) 29.sp else 39.sp,
                lineHeight = if (compact) 35.sp else 47.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(29.dp))
            content()
        }
    }
}

@Preview(name = "Phone portfolio", widthDp = 375, heightDp = 844)
@Preview(name = "Tablet portfolio", widthDp = 768, heightDp = 1024)
@Preview(name = "Desktop portfolio", widthDp = 1440, heightDp = 1000)
@Composable
private fun WebPortfolioPreview() {
    WebPortfolioScreen()
}
