package com.egoriku.network

import com.egoriku.network.Constants.TABLE_CATEGORIES
import com.egoriku.network.model.Config
import com.github.theapache64.retrosheet.annotations.Read
import retrofit2.http.GET

interface ApiInterface {

    @Read("SELECT *")
    @GET(TABLE_CATEGORIES)
    suspend fun getConfig(): List<Config>
}