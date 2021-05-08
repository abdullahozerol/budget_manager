package com.ozerol.budgetmanager.addexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozerol.budgetmanager.database.ExpenseDao
import com.ozerol.budgetmanager.database.LastCurrencyDao
import com.ozerol.budgetmanager.service.Repository

class FRAddExpenseViewModelFactory(
    private val repository: Repository,
    private val expenseId: Long,
    private val expenseData: ExpenseDao,
    private val lastCurrencyData: LastCurrencyDao
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FRAddExpenseViewModel::class.java)) {
            return FRAddExpenseViewModel(repository, expenseId, expenseData, lastCurrencyData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}