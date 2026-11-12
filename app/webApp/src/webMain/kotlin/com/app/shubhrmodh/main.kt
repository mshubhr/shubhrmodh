package com.app.shubhrmodh

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.app.shubhrmodh.components.launcher.WebPortfolioScreen
import com.app.shubhrmodh.navigation.NavKey

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        WebPortfolioScreen(onNavigate = { destination ->
            when (destination) {
                NavKey.About, NavKey.Experience, NavKey.Projects, NavKey.Contact, NavKey.Skills, NavKey.Achievements -> Unit
                else -> Unit
            }
        })
    }
}
