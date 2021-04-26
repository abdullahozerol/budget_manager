package com.ozerol.budgetmanager.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozerol.budgetmanager.database.ExpenseDao

class FRProfileViewModelFactory(private val expenseData: ExpenseDao, private val app: Application) :
    ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FRProfileViewModel::class.java)) {
                return FRProfileViewModel(expenseData, app) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
}