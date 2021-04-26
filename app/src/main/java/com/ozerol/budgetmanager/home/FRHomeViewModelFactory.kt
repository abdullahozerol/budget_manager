package com.ozerol.budgetmanager.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozerol.budgetmanager.database.ExpenseDao

class FRHomeViewModelFactory(private val expenseData: ExpenseDao, private val app: Application) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FRHomeViewModel::class.java)) {
            return FRHomeViewModel(expenseData, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}