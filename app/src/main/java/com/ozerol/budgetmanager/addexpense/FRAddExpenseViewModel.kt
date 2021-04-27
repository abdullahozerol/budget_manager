package com.ozerol.budgetmanager.addexpense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao
import kotlinx.coroutines.launch

class FRAddExpenseViewModel(private val expenseId: Long = 0L, private val expenseData: ExpenseDao) : ViewModel() {

    private var myExpense = MutableLiveData<Expense?>()
    val allExpense = expenseData.readAll()

    private val _toHome = MutableLiveData<Boolean?>()
    val toHome: LiveData<Boolean?>
        get() = _toHome

    private val _toAddExpensetoHome = MutableLiveData<Expense?>()
    val toAddExpense: LiveData<Expense?>
        get() = _toAddExpensetoHome

    fun addExpenses(id:Long,imageCategory: Int, description: String, cost:Double){
        viewModelScope.launch {
            val expense= expenseData.read(expenseId) ?:return@launch
            expense.imageCategory=imageCategory
            expense.description=description
            expense.cost=cost

            expenseData.update(expense)

            _toHome.value=true
        }
    }

}