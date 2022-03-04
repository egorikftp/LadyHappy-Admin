package com.egoriku.ladyhapppy.data.data.api

import com.egoriku.ladyhapppy.data.koin.NetworkModule
import com.github.theapache64.retrosheet.annotations.Read
import com.egoriku.ladyhapppy.data.data.model.Config
import com.egoriku.ladyhapppy.data.data.util.calladapter.flow.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiInterface {

    @Read("SELECT *")
    @GET(NetworkModule.TABLE_CATEGORIES)
    fun getConfig(): Flow<Resource<List<Config>>>
}