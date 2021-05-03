package com.ozerol.budgetmanager.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozerol.budgetmanager.database.ExpenseDao

class FRProfileViewModel (private val expenseData: ExpenseDao, private val app: Application) : AndroidViewModel(app)  {
    private val _toHome = MutableLiveData<Boolean?>()
    val toHome: LiveData<Boolean?>
        get() = _toHome

    fun clickBack(){
        _toHome.value=true
    }
}