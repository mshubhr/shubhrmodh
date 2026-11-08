package com.app.shubhrmodh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import com.app.shubhrmodh.components.*
import com.app.shubhrmodh.theme.BgDark
import com.app.shubhrmodh.theme.PortfolioTheme
import kotlinx.coroutines.launch

@Composable
fun App() {
    PortfolioTheme {
        var currentSection by remember { mutableStateOf(PortfolioSection.HOME) }
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()

        val sectionPositions = remember { mutableStateMapOf<PortfolioSection, Float>() }

        fun scrollTo(section: PortfolioSection) {
            currentSection = section
            val position = sectionPositions[section] ?: 0f
            coroutineScope.launch {
                scrollState.animateScrollTo(position.toInt().coerceAtLeast(0))
            }
        }

        Box(
            modifier = Modifier.fillMaxSize().background(BgDark)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(72.dp))

                Box(
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                        sectionPositions[PortfolioSection.HOME] = coordinates.positionInParent().y
                    }) {
                    HeroSection(
                        onExploreProjects = { scrollTo(PortfolioSection.PROJECTS) },
                        onContactClick = { scrollTo(PortfolioSection.CONTACT) })
                }

                Box(
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                        sectionPositions[PortfolioSection.ABOUT] = coordinates.positionInParent().y
                    }) {
                    AboutSection()
                }

                Box(
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                        sectionPositions[PortfolioSection.EXPERIENCE] =
                            coordinates.positionInParent().y
                    }) {
                    ExperienceSection()
                }

                Box(
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                        sectionPositions[PortfolioSection.PROJECTS] =
                            coordinates.positionInParent().y
                    }) {
                    ProjectsSection()
                }

                Box(
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                        sectionPositions[PortfolioSection.SKILLS] = coordinates.positionInParent().y
                    }) {
                    SkillsSection()
                }

                Box(
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                        sectionPositions[PortfolioSection.ACHIEVEMENTS] =
                            coordinates.positionInParent().y
                    }) {
                    AchievementsSection()
                }

                Box(
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                        sectionPositions[PortfolioSection.CONTACT] =
                            coordinates.positionInParent().y
                    }) {
                    ContactSection()
                }

                FooterSection()
            }

            Navbar(
                currentSection = currentSection,
                onNavigate = { section -> scrollTo(section) },
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}
