package com.egoriku.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

actual open class KmmViewModel {

    actual val viewModelScope: CoroutineScope = createViewModelScope()

    actual open fun onCleared() {
        viewModelScope.cancel()
    }
}

