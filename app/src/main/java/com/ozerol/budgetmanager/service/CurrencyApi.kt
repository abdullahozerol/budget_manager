package com.ozerol.budgetmanager.service

import com.ozerol.budgetmanager.service.models.Currencies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("latest")
    suspend fun getData(@Query("access_key") accesKey: String): Response<Currencies?>
}