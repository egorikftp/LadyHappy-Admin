package com.egoriku.ladyhapppy.data.koin

import com.egoriku.ladyhapppy.data.data.api.ApiInterface
import com.egoriku.ladyhapppy.data.data.repository.ConfigRepository
import com.egoriku.ladyhapppy.data.data.util.calladapter.flow.FlowResourceCallAdapterFactory
import com.egoriku.ladyhapppy.data.koin.NetworkModule.TABLE_CATEGORIES
import com.egoriku.ladyhappy.ui.ContentScreenModel
import com.github.theapache64.retrosheet.RetrosheetInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {
    const val TABLE_CATEGORIES = "categories"
}

val dataModule = module {

    factory { ContentScreenModel(configRepository = get()) }


    factory { ConfigRepository(apiInterface = get()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                RetrosheetInterceptor.Builder()
                    .setLogging(true)
                    .addSheet(
                        sheetName = TABLE_CATEGORIES,
                        "category_id",
                        "category_name",
                        "subcategory_id",
                        "subcategory_name",
                        "description"
                    )
                    .build()
            )
            .build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://docs.google.com/spreadsheets/d/1XrYNW2hX4lMxMhd8rOFzk5vrjYlxg3WxEKPAqDMhB54/")
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(FlowResourceCallAdapterFactory())
            .build()
    }

    single<ApiInterface> { get<Retrofit>().create(ApiInterface::class.java) }
}


