package com.ozerol.budgetmanager.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozerol.budgetmanager.database.ExpenseDao
import com.ozerol.budgetmanager.database.ProfileDao

class FRHomeViewModelFactory(
    private val expenseData: ExpenseDao,
    private val profileData: ProfileDao,
    private val app: Application
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FRHomeViewModel::class.java)) {
            return FRHomeViewModel(expenseData, profileData, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}