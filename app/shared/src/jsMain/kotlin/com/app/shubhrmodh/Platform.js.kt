package com.app.shubhrmodh

actual fun prefersReducedMotion(): Boolean =
    js("window.matchMedia('(prefers-reduced-motion: reduce)').matches") as Boolean
