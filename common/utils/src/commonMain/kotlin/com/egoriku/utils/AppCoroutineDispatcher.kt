package com.egoriku.utils

import kotlinx.coroutines.CoroutineDispatcher

expect object AppCoroutineDispatcher {
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
}