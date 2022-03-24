package com.egoriku.network

sealed class Resource<T> {

    class Loading<T>(
        val message: String? = null
    ) : Resource<T>()

    data class Success<T>(
        val data: T,
        val message: String? = null
    ) : Resource<T>()

    data class Error<T>(
        val errorData: String,
        val errorCode: Int? = null
    ) : Resource<T>()
}
