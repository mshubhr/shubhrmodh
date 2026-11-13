package com.app.shubhrmodh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.shubhrmodh.components.launcher.AndroidHomeScreen
import com.app.shubhrmodh.components.launcher.WebPortfolioScreen
import com.app.shubhrmodh.components.media.ExoPlayerScreen
import com.app.shubhrmodh.components.screens.AboutScreen
import com.app.shubhrmodh.components.screens.AchievementsScreen
import com.app.shubhrmodh.components.screens.ContactScreen
import com.app.shubhrmodh.components.screens.ExperienceScreen
import com.app.shubhrmodh.components.screens.ProjectsScreen
import com.app.shubhrmodh.components.screens.RecentsScreen
import com.app.shubhrmodh.components.screens.SkillsScreen
import com.app.shubhrmodh.navigation.NavDisplay
import com.app.shubhrmodh.navigation.NavKey
import com.app.shubhrmodh.navigation.rememberNav3BackStack
import com.app.shubhrmodh.theme.PortfolioTheme
import com.app.shubhrmodh.theme.PureBlack

@Composable
@Preview
fun App(
    initialKey: NavKey = NavKey.WebPortfolio
) {
    PortfolioTheme {
        val backStack = rememberNav3BackStack(initial = initialKey)

        Box(
            modifier = Modifier.fillMaxSize().background(PureBlack)
        ) {

            NavDisplay(backStack = backStack) { key ->
                when (key) {
                    is NavKey.WebPortfolio -> WebPortfolioScreen(
                        onToggleLauncherMode = { backStack.navigateTo(NavKey.Home) }
                    )

                    is NavKey.Home -> AndroidHomeScreen(
                        onNavigate = { dest -> backStack.navigateTo(dest) })

                    is NavKey.About -> AboutScreen(
                        onBack = { backStack.pop() })

                    is NavKey.Experience -> ExperienceScreen(
                        onBack = { backStack.pop() })

                    is NavKey.Projects -> ProjectsScreen(
                        onBack = { backStack.pop() })

                    is NavKey.ProjectDetail -> ProjectsScreen(
                        onBack = { backStack.pop() })

                    is NavKey.Skills -> SkillsScreen(
                        onBack = { backStack.pop() })

                    is NavKey.ExoPlayer -> ExoPlayerScreen(
                        onBack = { backStack.pop() })

                    is NavKey.Achievements -> AchievementsScreen(
                        onBack = { backStack.pop() })

                    is NavKey.Contact -> ContactScreen(
                        onBack = { backStack.pop() })

                    is NavKey.Recents -> RecentsScreen(onSelectKey = { dest ->
                        backStack.navigateTo(
                            dest
                        )
                    }, onClearAll = { backStack.popToHome() })
                }
            }
        }
    }
}
