package com.ozerol.budgetmanager.service

import com.ozerol.budgetmanager.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: CurrencyApi by lazy {
        retrofit.create(CurrencyApi::class.java)
    }
}