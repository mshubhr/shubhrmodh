package com.app.shubhrmodh

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.app.shubhrmodh.components.launcher.WebPortfolioScreen

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        WebPortfolioScreen()
    }
}
