package com.ozerol.budgetmanager.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao

class FRHomeViewModel(private val expenseData: ExpenseDao, app: Application) :
    AndroidViewModel(app) {

    var cost: String? = null
    val allExpense = expenseData.readAll()

    private var _toAddExpense = MutableLiveData<Expense?>()
    val toAddExpense: LiveData<Expense?>
        get() = _toAddExpense

    private val _toProfile = MutableLiveData<Boolean?>()
    val toProfile: LiveData<Boolean?>
        get() = _toProfile

    private val _toDetails = MutableLiveData<Long?>()
    val toDetails
        get() = _toDetails


    private val _totalExpense = MutableLiveData<Long?>()
    val totalExpense: LiveData<Long?>
        get() = _totalExpense



    fun clickAddExpense() {
        //      _toAddExpense.value = true
        _toAddExpense.value = Expense()

    }

    fun clickProfile() {
        _toProfile.value = true
    }

    fun clickDetail(id: Long) {
        _toDetails.value = id
    }

//    suspend fun totalExpense(): Long {
//        return expenseData.getTotalExpense()
//    }


}