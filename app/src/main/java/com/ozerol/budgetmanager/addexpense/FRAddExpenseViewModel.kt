package com.ozerol.budgetmanager.addexpense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao
import kotlinx.coroutines.launch

class FRAddExpenseViewModel(private val expenseId: Long = 0L, private val expenseData: ExpenseDao) :
    ViewModel() {


    val allExpense = expenseData.readAll()

    private var _myExpense = MutableLiveData<Expense?>()
    val myExpense: LiveData<Expense?>
        get() = _myExpense

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

    private val _selectCurrency = MutableLiveData<Int>()
    val selectCurrency: MutableLiveData<Int>
        get() = _selectCurrency

    init {
        _selectCurrency.postValue(R.id.rbTl)
    }


    fun onAddButtonClick() {
        viewModelScope.launch {
            val newExpense = Expense()
            //      expense.imageCategory = imageCategory
            newExpense.description = description.toString()
            newExpense.cost = cost?.toLong()!!
            newExpense.total = expenseData.getTotalExpense()?.plus(newExpense.cost) ?: newExpense.cost

            when (this@FRAddExpenseViewModel._selectExpenseType.value) {
                R.id.rbBill -> {
                    newExpense.imageCategory = "bill"
                }
                R.id.rbShopping -> {
                    newExpense.imageCategory = "shopping"
                }
                else -> newExpense.imageCategory = "other"
            }

            when (this@FRAddExpenseViewModel._selectCurrency.value) {
                R.id.rbTl -> {
                    newExpense.currency = "TL"
                }
                R.id.rbSt -> {
                    newExpense.currency = "STERLIN"
                }
                R.id.rbEu -> {
                    newExpense.currency = "EURO"
                }
                else -> newExpense.currency = "DOLAR"
            }

            expenseData.create(newExpense)
            expenseData.update(newExpense)

            _toHome.value = true
        }
    }


}