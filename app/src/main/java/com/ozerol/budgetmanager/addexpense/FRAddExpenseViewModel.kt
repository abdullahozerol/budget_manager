package com.ozerol.budgetmanager.addexpense

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao
import kotlinx.coroutines.launch

class FRAddExpenseViewModel(private val expenseId: Long = 0L, private val expenseData: ExpenseDao) :
    ViewModel() {


    val allExpense = expenseData.readAll()

    private var _myExpense = MutableLiveData<Expense?>()
    val myExpense: LiveData<Expense?>
        get() = _myExpense

    var imageCategory: Int = 0
    var description: String? = null
    var cost: String? = null

    private val _toHome = MutableLiveData<Boolean?>()
    val toHome: LiveData<Boolean?>
        get() = _toHome

    private val _toAddExpenseToHome = MutableLiveData<Expense?>()
    val toAddExpense: LiveData<Expense?>
        get() = _toAddExpenseToHome

//    fun addExpenses(imageCategory: Int, description: String, cost: Double) {
//        viewModelScope.launch {
//            val expense = expenseData.read(expenseId) ?: return@launch
//            expense.imageCategory = imageCategory
//            expense.description = description
//            expense.cost = cost
//
//            expenseData.update(expense)
//
//            _toHome.value = true
//        }
//    }

    fun onAddButtonClick() {
        viewModelScope.launch {
            val newExpense = Expense()
      //      expense.imageCategory = imageCategory
            newExpense.description = description.toString()
            newExpense.cost = cost?.toLong()!!

            expenseData.create(newExpense)

            _toHome.value = true
        }
    }
}