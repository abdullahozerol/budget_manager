package com.ozerol.budgetmanager.detailexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozerol.budgetmanager.database.ExpenseDao

class FRDetailExpenseViewModelFactory(
    private val expenseId: Long,
    private val expenseData: ExpenseDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FRDetailExpenseViewModel::class.java)) {
            return FRDetailExpenseViewModel(expenseId, expenseData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}