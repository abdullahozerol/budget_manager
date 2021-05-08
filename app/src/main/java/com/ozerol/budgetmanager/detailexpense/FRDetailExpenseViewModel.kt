package com.ozerol.budgetmanager.detailexpense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao
import kotlinx.coroutines.launch

class FRDetailExpenseViewModel(
    private val expenseId: Long = 0L,
    private val expenseData: ExpenseDao
) : ViewModel() {

    private val expense: LiveData<Expense?>
    fun getExpense() = expense

    init {
        expense = expenseData.readWithId(expenseId)
    }

    private val _toHome = MutableLiveData<Boolean?>()
    val toHome: LiveData<Boolean?>
        get() = _toHome

    fun clickDeleteExpense() {
        viewModelScope.launch {
            val expense = expenseData.read(expenseId) ?: return@launch
            expenseData.delete(expense)

            _toHome.value = true
        }
    }

    fun clickBack() {
        _toHome.value = true
    }
}