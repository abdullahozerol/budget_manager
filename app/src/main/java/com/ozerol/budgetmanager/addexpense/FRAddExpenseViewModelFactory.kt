package com.ozerol.budgetmanager.addexpense

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozerol.budgetmanager.database.ExpenseDao

class FRAddExpenseViewModelFactory(private val expenseId: Long, private val expenseData: ExpenseDao) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FRAddExpenseViewModel::class.java)) {
            return FRAddExpenseViewModel(expenseId,expenseData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}