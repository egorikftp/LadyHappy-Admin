package com.egoriku.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<T> : KmmViewModel() {

    open var initResult: T? = null
    abstract val loadingResult: T

    private val _resultStateFlow by lazy { MutableStateFlow(initResult ?: loadingResult) }
    val resultStateFlow: StateFlow<T> by lazy { _resultStateFlow.asStateFlow() }

    fun launch(block: suspend CoroutineScope.() -> T) {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                loadingResult?.let { loading -> onResult(loading) }
                onResult(block())
            } catch (e: Throwable) {
                onResult(errorResult(e))
            }
        }.start()
    }

    private suspend inline fun onResult(result: T) = withContext(Dispatchers.Main) {
        _resultStateFlow.value = result
    }

    abstract fun errorResult(throwable: Throwable): T
}