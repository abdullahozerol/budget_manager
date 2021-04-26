package com.ozerol.budgetmanager.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao

class FRHomeViewModel(private val expenseData: ExpenseDao, private val app: Application) :
    AndroidViewModel(app) {
    private var myExpense = MutableLiveData<Expense?>()
    private val allExpense = expenseData.readAll()

    private val _toAddExpense = MutableLiveData<Boolean?>()
    val toAddExpense: LiveData<Boolean?>
        get() = _toAddExpense

    private val _toProfile = MutableLiveData<Boolean?>()
    val toProfile: LiveData<Boolean?>
        get() = _toProfile

    fun clickAddExpense() {
        _toAddExpense.value = true
    }

    fun clickProfile() {
        _toProfile.value = true
    }


}