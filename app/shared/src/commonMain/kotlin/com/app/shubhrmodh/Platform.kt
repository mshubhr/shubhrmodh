package com.app.shubhrmodh

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun prefersReducedMotion(): Boolean
