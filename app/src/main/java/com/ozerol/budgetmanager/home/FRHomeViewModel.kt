package com.ozerol.budgetmanager.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao

class FRHomeViewModel(private val expenseData: ExpenseDao, private val app: Application) : AndroidViewModel(app) {
    private var myExpense = MutableLiveData<Expense?>()
    private val allExpense= expenseData.readAll()

    private val _toAddExpense = MutableLiveData<Boolean?>()
    val toAddExpense: LiveData<Boolean?>
    get() = _toAddExpense

    fun clickAddExpense(){
        _toAddExpense.value=true
    }


}