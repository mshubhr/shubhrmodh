package com.app.shubhrmodh

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.app.shubhrmodh.navigation.NavKey

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        App(initialKey = NavKey.WebPortfolio)
    }
}
