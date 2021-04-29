package com.ozerol.budgetmanager.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao

class FRHomeViewModel(private val expenseData: ExpenseDao, private val app: Application) :
    AndroidViewModel(app) {

    val allExpense = expenseData.readAll()

    private var _myExpense = MutableLiveData<Expense?>()
    val myExpense: LiveData<Expense?>
        get() = _myExpense

    private val _toAddExpense = MutableLiveData<Boolean?>()
    val toAddExpense: LiveData<Boolean?>
        get() = _toAddExpense

    private val _toProfile = MutableLiveData<Boolean?>()
    val toProfile: LiveData<Boolean?>
        get() = _toProfile

    fun clickAddExpense() {
  //      _toAddExpense.value = true
        _myExpense.value= Expense()

    }

    fun clickProfile() {
        _toProfile.value = true
    }


}