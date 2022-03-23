package com.egoriku.config

import kotlinx.coroutines.CoroutineDispatcher

expect object AppCoroutineDispatcher {
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
}