package com.egoriku.ladyhapppy.network

import com.egoriku.ladyhapppy.Constants.TABLE_CATEGORIES
import com.egoriku.ladyhapppy.Resource
import com.egoriku.ladyhapppy.network.model.Config
import com.github.theapache64.retrosheet.annotations.Read
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiInterface {

    @Read("SELECT *")
    @GET(TABLE_CATEGORIES)
    fun getConfig(): Flow<Resource<List<Config>>>
}