package com.app.shubhrmodh

import kotlin.js.ExperimentalWasmJsInterop

@OptIn(ExperimentalWasmJsInterop::class)
actual fun prefersReducedMotion(): Boolean =
    js("window.matchMedia('(prefers-reduced-motion: reduce)').matches")
