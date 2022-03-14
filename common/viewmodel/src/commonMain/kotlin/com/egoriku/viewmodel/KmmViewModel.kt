package com.egoriku.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.native.concurrent.ThreadLocal

expect open class KmmViewModel() {

    val viewModelScope: CoroutineScope

    open fun onCleared()
}

/**
 * Factory of viewModelScope. Copied from moko-mvvm library.
 */
@ThreadLocal
var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(context = Dispatchers.Main)
}
