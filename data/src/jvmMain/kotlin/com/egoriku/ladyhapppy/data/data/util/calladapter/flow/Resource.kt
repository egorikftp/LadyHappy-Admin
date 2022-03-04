package com.egoriku.ladyhapppy.data.data.util.calladapter.flow

/**
 * Created by theapache64 : Jul 26 Sun,2020 @ 13:22
 */
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
