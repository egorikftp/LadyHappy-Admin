package com.egoriku.network.flow

import com.egoriku.network.Resource
import com.egoriku.network.Resource.Error
import com.egoriku.network.Resource.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.awaitResponse
import java.lang.reflect.Type

/**
 * To convert retrofit response to Flow<Resource<T>>.
 * Inspired from FlowCallAdapterFactory
 */
class FlowResourceCallAdapter<R>(
    private val responseType: Type,
    private val isSelfExceptionHandling: Boolean
) : CallAdapter<R, Flow<Resource<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>) = flow<Resource<R>> {

        // Firing loading resource
        emit(Resource.Loading())

        val resp = call.awaitResponse()

        if (resp.isSuccessful) {
            resp.body()?.let { data ->
                // Success
                emit(Success(data))
            } ?: kotlin.run {
                // Error
                emit(Error("Response can't be null", resp.code()))
            }
        } else {
            // Error
            val errorBody = resp.message()
            emit(Error(errorBody, resp.code()))
        }

    }.catch { error: Throwable ->
        if (isSelfExceptionHandling) {
            emit(Error(error.message ?: "Something went wrong", -1))
        } else {
            throw error
        }
    }
}
