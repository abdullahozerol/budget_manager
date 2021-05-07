package com.ozerol.budgetmanager.service

import com.ozerol.budgetmanager.service.models.Currencies
import retrofit2.Response
import retrofit2.http.Query

class Repository {
    suspend fun getData(@Query("access_key") accesKey: String): Response<Currencies?> {
        return RetrofitInstance.api.getData(accesKey)
    }
}