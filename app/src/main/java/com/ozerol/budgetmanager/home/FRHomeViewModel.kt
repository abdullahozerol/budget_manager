package com.ozerol.budgetmanager.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ozerol.budgetmanager.R
import com.ozerol.budgetmanager.database.Expense
import com.ozerol.budgetmanager.database.ExpenseDao
import com.ozerol.budgetmanager.database.LastCurrencyDao
import com.ozerol.budgetmanager.database.ProfileDao
import com.ozerol.budgetmanager.service.Repository
import com.ozerol.budgetmanager.service.models.Currencies
import kotlinx.coroutines.launch
import retrofit2.Response

@Suppress("DEPRECATION")
class FRHomeViewModel(
    private val repository: Repository,
    private val expenseData: ExpenseDao,
    private val profileData: ProfileDao,
    private val lastCurrencyData: LastCurrencyDao,
    app: Application
) :
    AndroidViewModel(app) {

    var cost: String? = null
    val allExpense = expenseData.readAll()
    val totalExpense = expenseData.getTotal()
    val lastProfile = profileData.readLast()

    private val currencies = MutableLiveData<Currencies?>()

    private var _toAddExpense = MutableLiveData<Expense?>()
    val toAddExpense: LiveData<Expense?>
        get() = _toAddExpense

    private val _toProfile = MutableLiveData<Boolean?>()
    val toProfile: LiveData<Boolean?>
        get() = _toProfile

    private val _toDetails = MutableLiveData<Long?>()
    val toDetails
        get() = _toDetails

    private val _selectCurrencyButton = MutableLiveData<Int>()
    val selectCurrencyButton: MutableLiveData<Int>
        get() = _selectCurrencyButton

    init {
        _selectCurrencyButton.postValue(R.id.btnTl)
    }

    val myResponse = MutableLiveData<Response<Currencies?>>()
    fun getData() {
        viewModelScope.launch {
            val response = repository.getData("35c35c8d7bece1ddd065206c796b1411")
            if (response.isSuccessful) {
                myResponse.value = response
            } else Log.d("response: ", response.errorBody().toString())

        }
    }

    fun clickAddExpense() {
        _toAddExpense.value = Expense()
    }

    fun clickProfile() {
        _toProfile.value = true
    }

    fun clickDetail(id: Long) {
        _toDetails.value = id
    }
}