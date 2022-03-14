package com.egoriku.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewModelScope as extViewModelScope

actual open class KmmViewModel : ViewModel() {

    actual val viewModelScope: CoroutineScope
        get() = extViewModelScope

    public actual override fun onCleared() = super.onCleared()
}
