package com.ozerol.budgetmanager.addexpense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao
import kotlinx.coroutines.launch

enum class ExpenseType(val value: Byte) {
    BILL(0),
    SHOPPING(1),
    OTHER(2),
}

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

    private val _selectExpenseType = MutableLiveData<Int>()
    val selectExpenseType: MutableLiveData<Int>
        get() = _selectExpenseType

    init {
        _selectExpenseType.postValue(R.id.rbOther)
    }


    fun onAddButtonClick() {
        viewModelScope.launch {
            val newExpense = Expense()
            //      expense.imageCategory = imageCategory
            newExpense.description = description.toString()
            newExpense.cost = cost?.toLong()!!

            when (this@FRAddExpenseViewModel._selectExpenseType.value) {
                R.id.rbBill -> {
                    newExpense.imageCategory = "bill"
                }
                R.id.rbShopping -> {
                    newExpense.imageCategory = "shopping"
                }
                else -> newExpense.imageCategory = "other"
            }

            expenseData.create(newExpense)

            _toHome.value = true
        }
    }


}