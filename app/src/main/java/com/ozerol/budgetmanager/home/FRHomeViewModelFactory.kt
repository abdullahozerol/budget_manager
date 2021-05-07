package com.ozerol.budgetmanager.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozerol.budgetmanager.database.ExpenseDao
import com.ozerol.budgetmanager.database.LastCurrencyDao
import com.ozerol.budgetmanager.database.ProfileDao
import com.ozerol.budgetmanager.service.Repository

class FRHomeViewModelFactory(
    private val repository: Repository,
    private val expenseData: ExpenseDao,
    private val profileData: ProfileDao,
    private val lastCurrencyData: LastCurrencyDao,
    private val app: Application
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FRHomeViewModel::class.java)) {
            return FRHomeViewModel(repository, expenseData, profileData, lastCurrencyData, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}