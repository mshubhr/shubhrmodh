package com.app.shubhrmodh.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.app.shubhrmodh.data.ProjectItem

sealed interface NavKey {
    val title: String

    data object Home : NavKey {
        override val title = "Launcher"
    }

    data object About : NavKey {
        override val title = "Device Info (About Me)"
    }

    data object Experience : NavKey {
        override val title = "Work History"
    }

    data object Projects : NavKey {
        override val title = "Projects"
    }

    data class ProjectDetail(val project: ProjectItem) : NavKey {
        override val title = project.name
    }

    data object Skills : NavKey {
        override val title = "Tech Stack"
    }

    data object ExoPlayer : NavKey {
        override val title = "ExoPlayer Media"
    }

    data object Achievements : NavKey {
        override val title = "Honors"
    }

    data object Contact : NavKey {
        override val title = "Phone & Dial"
    }

    data object Recents : NavKey {
        override val title = "Overview"
    }
}

class Nav3BackStack(
    initial: NavKey = NavKey.Home
) {
    val stack = mutableStateListOf<NavKey>(initial)

    val current: NavKey
        get() = stack.lastOrNull() ?: NavKey.Home

    val canGoBack: Boolean
        get() = stack.size > 1

    fun navigateTo(key: NavKey) {
        if (key is NavKey.Home) {
            stack.clear()
            stack.add(NavKey.Home)
            return
        }
        if (stack.lastOrNull() != key) {
            stack.add(key)
        }
    }

    fun pop(): Boolean {
        if (canGoBack) {
            stack.removeLastOrNull()
            return true
        }
        return false
    }

    fun popToHome() {
        stack.clear()
        stack.add(NavKey.Home)
    }

    fun removeEntry(key: NavKey) {
        if (stack.size > 1) {
            stack.remove(key)
        }
    }
}

@Composable
fun rememberNav3BackStack(initial: NavKey = NavKey.Home): Nav3BackStack {
    return remember { Nav3BackStack(initial) }
}

@Composable
fun NavDisplay(
    backStack: Nav3BackStack,
    modifier: Modifier = Modifier,
    content: @Composable (NavKey) -> Unit
) {
    AnimatedContent(
        targetState = backStack.current,
        transitionSpec = {
            if (targetState == NavKey.Home) {
                (fadeIn(animationSpec = tween(220)) + scaleIn(initialScale = 0.92f, animationSpec = tween(220)))
                    .togetherWith(fadeOut(animationSpec = tween(180)) + scaleOut(targetScale = 1.05f, animationSpec = tween(180)))
            } else if (initialState == NavKey.Home) {
                (fadeIn(animationSpec = tween(260)) + scaleIn(initialScale = 1.05f, animationSpec = tween(260)))
                    .togetherWith(fadeOut(animationSpec = tween(200)) + scaleOut(targetScale = 0.95f, animationSpec = tween(200)))
            } else {
                (slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(240)) + fadeIn(animationSpec = tween(240)))
                    .togetherWith(slideOutHorizontally(targetOffsetX = { -it / 3 }, animationSpec = tween(240)) + fadeOut(animationSpec = tween(200)))
            }
        },
        modifier = modifier.fillMaxSize(),
        label = "Nav3SceneTransition"
    ) { key ->
        Box(modifier = Modifier.fillMaxSize()) {
            content(key)
        }
    }
}
